package com.fikrisandi.todo.list

import androidx.paging.PagingData
import com.fikrisandi.model.dto.todo.TodoDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow


data class TodoListState(
    val pagedData: Flow<PagingData<TodoDto>> = emptyFlow()
)

sealed class TodoListEvent{

}