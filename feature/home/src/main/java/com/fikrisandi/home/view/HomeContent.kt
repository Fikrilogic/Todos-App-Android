package com.fikrisandi.home.view

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FilledTonalIconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fikrisandi.framework.base.BaseUiState
import com.fikrisandi.framework.extension.cast
import com.fikrisandi.home.HomeState
import com.fikrisandi.model.dto.todo.TodoDto
import com.fikrisandi.provider.EmptyNavigationProvider
import com.fikrisandi.provider.NavigationProvider
import com.fikrisandi.provider.TodoFlagType
import com.fikrisandi.theme.TodoTheme
import com.fikrisandi.theme.TodosColors
import com.fikrisandi.theme.TodosTypography
import com.fikrisandi.todo.view.TodoContentSheetScreen
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeContent(
    modifier: Modifier = Modifier,
    state: BaseUiState<*>,
    navController: NavigationProvider = EmptyNavigationProvider(),
    onCreateTodo: (TodoDto) -> Unit = {},
) {
    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    var openBottomSheet by remember {
        mutableStateOf(false)
    }

    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    LaunchedEffect(openBottomSheet) {
        if (!openBottomSheet) sheetState.hide() else sheetState.expand()
    }

    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Set Your Daily To Do",
                        style = TodosTypography.headlineLarge,
                        color = TodosColors.background
                    )
                }, colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = TodosColors.primary,
                    titleContentColor = TodosColors.background
                )
            )
        },
        floatingActionButton = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp)
            ) {
                FilledTonalIconButton(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = { openBottomSheet = true; scope.launch { sheetState.expand() } },
                    shape = RoundedCornerShape(4.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Add,
                            contentDescription = "Add New Todo",
                            tint = TodosColors.primary,
                        )
                        Text(
                            text = "Create New Todo",
                            style = TodosTypography.labelLarge,
                            color = TodosColors.primary
                        )
                    }
                }
            }
        },
        floatingActionButtonPosition = FabPosition.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it), verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            HomeTodosLastAdded(
                modifier = Modifier.fillMaxWidth(),
                state,
                onShowDetailTodo = {
                    navController.openTodoDetail(it)
                },
                onShowMore = {
                    navController.openTodoList(TodoFlagType.ON_PROGRESS)
                }
            )

            when (state) {
                is BaseUiState.Loading -> {}
                is BaseUiState.Empty -> {}
                is BaseUiState.Data -> {
                    val data = state.cast<BaseUiState.Data<HomeState>>()
                    if (data.value.listTodoCompleted.isNotEmpty()) {
                        HomeTodosCompleteList(
                            modifier = Modifier.fillMaxWidth(),
                            listTodo = data.value.listTodoCompleted,
                            onShowMore = { navController.openTodoList(TodoFlagType.COMPLETED) },
                            onShowTodoDetail = { navController.openTodoDetail(it) }
                        )
                    }
                }

                is BaseUiState.Error -> {}
            }
        }

        if (openBottomSheet) {
            ModalBottomSheet(
                onDismissRequest = { openBottomSheet = false },
                sheetState = sheetState
            ) {
                TodoContentSheetScreen(
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
                                onCreateTodo.invoke(todo)
                                openBottomSheet = false
                            }
                        }
                    })
            }
        }
    }
}

@Composable
@Preview
fun HomeContentPreview() {

    TodoTheme {
        HomeContent(modifier = Modifier.fillMaxSize(), BaseUiState.Data<HomeState>(HomeState()))
    }
}