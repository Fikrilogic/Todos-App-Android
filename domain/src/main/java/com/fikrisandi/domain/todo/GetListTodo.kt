package com.fikrisandi.domain.todo

import com.fikrisandi.framework.usecase.LocalUseCase
import com.fikrisandi.model.dto.todo.TodoDto
import com.fikrisandi.model.dto.todo.toDto
import com.fikrisandi.repository.todo.TodoRepository
import kotlinx.coroutines.flow.FlowCollector
import javax.inject.Inject

class GetListTodo @Inject constructor(private val repository: TodoRepository) :
    LocalUseCase<GetListTodo.Params, List<TodoDto>>() {
    data class Params(
        val lastAdded: Boolean = false,
        val isCompleted: Boolean = false,
    )

    override suspend fun FlowCollector<List<TodoDto>>.execute(params: Params) {
        val listData = when {
            params.lastAdded -> repository.get()
            params.isCompleted -> repository.getLastCompleted()
            else -> repository.get()
        }

        emit(listData.map { it.toDto() })
    }
}