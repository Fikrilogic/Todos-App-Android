package com.fikrisandi.todo.list

import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.fikrisandi.provider.NavigationProvider
import com.fikrisandi.provider.TodoFlagType
import com.fikrisandi.todo.list.view.TodoListContent
import com.fikrisandi.todo.view.TodoContentSheetScreen
import com.ramcosta.composedestinations.annotation.Destination
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Destination(start = true)
@Composable
fun TodoListScreen(
    modifier: Modifier = Modifier,
    navController: NavigationProvider,
    flag: TodoFlagType = TodoFlagType.ON_PROGRESS,
    viewModel: TodoListViewModel = hiltViewModel()
) {
    val uiState = viewModel.uiState.collectAsState()
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    var openBottomSheet by remember {
        mutableStateOf(false)
    }

    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    LaunchedEffect(openBottomSheet) {
        if (!openBottomSheet) sheetState.hide() else sheetState.expand()
    }

    LaunchedEffect(viewModel) {
        val eventType =
            if (flag == TodoFlagType.COMPLETED) TodoListEvent.LoadTodoCompleted
            else TodoListEvent.LoadTodoLastAdded
        viewModel.onTrigger(eventType)
    }

    LaunchedEffect(key1 = viewModel.onAddedEvent, block = {
        if (viewModel.onAddedEvent) {
            val eventType =
                if (flag == TodoFlagType.COMPLETED) TodoListEvent.LoadTodoCompleted
                else TodoListEvent.LoadTodoLastAdded
            viewModel.onTrigger(eventType)
            viewModel.onAddedEvent = false
        }
    })

    TodoListContent(
        modifier = modifier,
        state = uiState.value,
        navController = navController,
        openBottomSheet = {
            scope.launch { sheetState.expand() }.invokeOnCompletion {
                openBottomSheet = true
            }
        }
    )

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
                            viewModel.onTrigger(TodoListEvent.CreateNewTodo(todo))
                            openBottomSheet = false
                        }
                    }
                })
        }
    }
}