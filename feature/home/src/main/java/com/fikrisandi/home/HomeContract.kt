package com.fikrisandi.home

import androidx.paging.PagingData
import com.fikrisandi.model.dto.todo.TodoDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow


data class HomeState(
    val listTodoAdded: Flow<PagingData<TodoDto>> = emptyFlow(),
    val listTodoCompleted: List<TodoDto> = emptyList(),
)

sealed class HomeEvent{
    object LoadTodosAdded: HomeEvent()
    object LoadTodoCompleted: HomeEvent()
}