package com.fikrisandi.home

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.fikrisandi.domain.todo.AddTodo
import com.fikrisandi.domain.todo.GetListPagingTodo
import com.fikrisandi.domain.todo.GetListTodo
import com.fikrisandi.framework.base.BaseUiState
import com.fikrisandi.framework.base.MvviViewModel
import com.fikrisandi.model.dto.todo.TodoDto
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getListPagingTodo: GetListPagingTodo,
    private val getListTodo: GetListTodo,
    private val addTodo: AddTodo
) : MvviViewModel<BaseUiState<HomeState>, HomeEvent>() {

    private val config = PagingConfig(initialLoadSize = 5, prefetchDistance = 5, pageSize = 20)
    override fun onTrigger(eventType: HomeEvent) {
        when (eventType) {
            is HomeEvent.LoadTodoCompleted -> {
                loadListTodoCompleted()
            }

            is HomeEvent.LoadTodosAdded -> {
                loadListTodoAdded()
            }

            is HomeEvent.AddNewTodo -> {
                addNewTodo(eventType.todo, eventType.callback)
            }
        }
    }


    private fun loadListTodoCompleted() = safeLaunch {
        val params = GetListTodo.Params(
            isCompleted = true
        )
        val existData = (uiState.value as? BaseUiState.Data<HomeState>)?.value

        call(
            getListTodo(params)
        ) {
            when (existData) {
                null -> {
                    setState(BaseUiState.Data(HomeState(listTodoCompleted = it)))
                }

                else -> {
                    setState(BaseUiState.Data(existData.copy(listTodoCompleted = it)))
                }
            }

        }
    }

    private fun loadListTodoAdded() = safeLaunch {
        val params = GetListPagingTodo.Params(
            pagingConfig = config,
            options = mapOf(
                "limit" to 5,
                "lastAdded" to true
            )
        )
        val existData = (uiState.value as? BaseUiState.Data<HomeState>)?.value
        val flowData = getListPagingTodo(params).cachedIn(viewModelScope)

        when (existData) {
            null -> {
                setState(BaseUiState.Data(HomeState(listTodoAdded = flowData)))
            }

            else -> {
                setState(BaseUiState.Data(existData.copy(listTodoAdded = flowData)))
            }
        }
    }


    private fun addNewTodo(todo: TodoDto, callback: () -> Unit) = safeLaunch {
        val params = AddTodo.Params(todo)
        call(addTodo(params)) {
            callback.invoke()
        }
    }
}