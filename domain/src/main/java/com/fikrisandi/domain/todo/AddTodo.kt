package com.fikrisandi.domain.todo

import com.fikrisandi.framework.usecase.LocalUseCase
import com.fikrisandi.model.dto.todo.TodoDto
import com.fikrisandi.model.dto.todo.toEntity
import com.fikrisandi.repository.todo.TodoRepository
import kotlinx.coroutines.flow.FlowCollector
import javax.inject.Inject

class AddTodo @Inject constructor(val repository: TodoRepository): LocalUseCase<AddTodo.Params, Unit>() {

    data class Params(
        val todo: TodoDto
    )

    override suspend fun FlowCollector<Unit>.execute(params: Params) {
        repository.insert(params.todo.toEntity())
    }
}