package com.fikrisandi.domain.todo

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.fikrisandi.framework.usecase.FlowPagingUseCase
import com.fikrisandi.framework.usecase.LocalUseCase
import com.fikrisandi.model.dto.todo.TodoDto
import com.fikrisandi.repository.todo.TodoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import javax.inject.Inject


class GetListTodo @Inject constructor(private val repository: TodoRepository): FlowPagingUseCase<GetListTodo.Params, TodoDto>() {

    data class Params(
        val pagingConfig: PagingConfig,
        val options: Map<String, Any>
    )

    override fun execute(params: Params): Flow<PagingData<TodoDto>> {
        return Pager(
            config = params.pagingConfig,
            pagingSourceFactory = {
                TodoPagingSource(repository, params.options)
            }
        ).flow
    }

}