package com.fikrisandi.domain.todo

import com.fikrisandi.framework.usecase.LocalUseCase
import com.fikrisandi.model.dto.todo.TodoDto
import com.fikrisandi.model.dto.todo.toEntity
import com.fikrisandi.repository.todo.TodoRepository
import kotlinx.coroutines.flow.FlowCollector
import javax.inject.Inject


class DeleteTodo @Inject constructor(val repository: TodoRepository): LocalUseCase<DeleteTodo.Params, Unit>() {

    data class Params(
        val todo: TodoDto
    )

    override suspend fun FlowCollector<Unit>.execute(params: Params) {
        repository.delete(params.todo.toEntity())
        emit(Unit)
    }
}