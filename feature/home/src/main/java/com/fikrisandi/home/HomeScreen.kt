package com.fikrisandi.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.fikrisandi.framework.base.BaseUiState
import com.fikrisandi.framework.extension.cast
import com.fikrisandi.model.dto.todo.TodoDto
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator


@RootNavGraph(start = true)
@Destination
@Composable
fun HomeScreen(
    navController: DestinationsNavigator,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel()
) {

    LaunchedEffect(Unit) {
        viewModel.onTrigger(HomeEvent.LoadTodosAdded)
    }

    LaunchedEffect(Unit) {
        viewModel.onTrigger(HomeEvent.LoadTodoCompleted)
    }

    val uiState by viewModel.uiState.collectAsState()
    HomeContent(
        modifier = modifier.fillMaxSize(),
        uiState,
        onCreateTodo = { viewModel.onTrigger(HomeEvent.AddNewTodo(it)) }
    )

}