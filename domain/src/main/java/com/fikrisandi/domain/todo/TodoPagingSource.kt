package com.fikrisandi.domain.todo

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.fikrisandi.model.dto.todo.TodoDto
import com.fikrisandi.model.dto.todo.toDto
import com.fikrisandi.repository.todo.TodoRepository
import kotlinx.coroutines.flow.asFlow

class TodoPagingSource(val repository: TodoRepository, val options: Map<String, Any>): PagingSource<Int, TodoDto>() {
    override fun getRefreshKey(state: PagingState<Int, TodoDto>): Int? {
        return state.anchorPosition?.let {position ->
            val anchorPage = state.closestPageToPosition(position)
            anchorPage?.prevKey?.plus(1) ?:anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, TodoDto> {
        val page = params.key ?: 1
        val limit = (options["limit"] as? Int) ?: 10
        val offset = page * limit
        return try {
            val listTodoEntity = repository.get(limit = limit, offset = offset).map {
                it.toDto()
            }
            LoadResult.Page(
                data = listTodoEntity,
                prevKey = if(page == 0) null else page - 1,
                nextKey = if(listTodoEntity.isEmpty()) null else page + 1,
            )

        } catch (e: Throwable){
            LoadResult.Error(e)
        }
    }
}