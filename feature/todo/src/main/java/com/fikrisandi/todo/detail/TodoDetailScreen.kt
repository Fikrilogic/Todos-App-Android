package com.fikrisandi.todo.detail

import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.fikrisandi.component.widget.TodoLoaderDialog
import com.fikrisandi.model.dto.todo.TodoDto
import com.fikrisandi.provider.EmptyNavigationProvider
import com.fikrisandi.provider.NavigationProvider
import com.fikrisandi.todo.detail.view.TodoDetailContent
import com.ramcosta.composedestinations.annotation.Destination
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

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
    if (viewModel.loading) {
        TodoLoaderDialog()
    }

    if(viewModel.alert.first){
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
        }
    )
}