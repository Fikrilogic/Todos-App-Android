package com.fikrisandi.todo.detail

import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.fikrisandi.component.widget.TodoLoaderDialog
import com.fikrisandi.model.dto.todo.TodoDto
import com.fikrisandi.provider.NavigationProvider
import com.fikrisandi.todo.detail.view.TodoDetailContent
import com.fikrisandi.todo.view.TodoContentSheetScreen
import com.ramcosta.composedestinations.annotation.Destination
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Destination
@Composable
fun TodoDetailScreen(
    modifier: Modifier = Modifier,
    navController: NavigationProvider,
    state: TodoDto = TodoDto(),
    viewModel: TodoDetailViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    var openBottomSheet by remember {
        mutableStateOf(false)
    }

    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    if (viewModel.loading) {
        TodoLoaderDialog()
    }

    if (viewModel.alert.first) {
        Toast.makeText(context, viewModel.alert.second, Toast.LENGTH_LONG).show()
    }

    TodoDetailContent(
        modifier = modifier,
        state = state,
        navController = navController,
        onDeleteClick = {
            viewModel.deleteData(state) {
                scope.launch {
                    viewModel.startLoading()
                    Toast.makeText(context, it, Toast.LENGTH_LONG).show()
                    delay(1500)
                }.invokeOnCompletion {
                    navController.navigateUp()
                }
            }
        },
        onUpdateClick = {
            openBottomSheet = true
        }
    )

    if (openBottomSheet) {
        ModalBottomSheet(
            onDismissRequest = { openBottomSheet = false },
            sheetState = sheetState
        ) {
            TodoContentSheetScreen(
                state = state,
                modifier = Modifier.fillMaxWidth(),
                onCancel = { openBottomSheet = false },
                onSubmit = { todo ->
                    when {
                        todo.title.isEmpty() -> {
                            Toast.makeText(context, "Title Must Not Empty", Toast.LENGTH_SHORT).show()
                        }
                        todo.dueDateNotSelected() -> {
                            Toast.makeText(context, "Date Must Not Empty", Toast.LENGTH_SHORT).show()
                        }
                        else -> {
                            viewModel.updateData(todo) {
                                scope.launch {
                                    viewModel.startLoading()
                                    Toast.makeText(context, it, Toast.LENGTH_LONG).show()
                                    delay(1500)
                                }.invokeOnCompletion {
                                    navController.navigateUp()
                                }
                            }

                        }
                    }
                })
        }
    }
}