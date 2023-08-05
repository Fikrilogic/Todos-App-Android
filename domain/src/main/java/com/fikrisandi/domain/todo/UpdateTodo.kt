package com.fikrisandi.domain.todo

import com.fikrisandi.framework.usecase.LocalUseCase
import com.fikrisandi.model.dto.todo.TodoDto
import com.fikrisandi.model.dto.todo.toEntity
import com.fikrisandi.repository.todo.TodoRepository
import kotlinx.coroutines.flow.FlowCollector
import javax.inject.Inject

class UpdateTodo @Inject constructor(private val repository: TodoRepository) :
    LocalUseCase<UpdateTodo.Params, Unit>() {

    data class Params(
        val todo: TodoDto
    )

    override suspend fun FlowCollector<Unit>.execute(params: Params) {
        try {
            when (val data = repository.getById(params.todo.id)) {
                null -> repository.insert(params.todo.toEntity())
                else -> repository.update(params.todo.toEntity().let {
                    it.id = data.id
                    it
                })
            }
            emit(Unit)
        } catch (e: Exception){
            error(e)
        }
    }
}