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
class HomeViewModel @Inject constructor(private val getListPagingTodo: GetListPagingTodo, private val getListTodo: GetListTodo, private val addTodo: AddTodo) : MvviViewModel<BaseUiState<HomeState>,HomeEvent>() {


    private val config = PagingConfig(initialLoadSize = 5, prefetchDistance = 5, pageSize = 20)
    override fun onTrigger(eventType: HomeEvent) {
        when(eventType){
            is HomeEvent.LoadTodoCompleted -> {
                loadListTodoCompleted()
            }
            is HomeEvent.LoadTodosAdded -> {
                loadListTodoAdded()
            }
            is HomeEvent.AddNewTodo ->{
                addNewTodo(eventType.todo)
            }
        }
    }


    private fun loadListTodoCompleted() = safeLaunch {
        val params = GetListTodo.Params(
            isCompleted = true
        )
        call(
            getListTodo(params)
        ){
            setState(BaseUiState.Data(HomeState(listTodoCompleted = it)))
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
        val flowData = getListPagingTodo(params).cachedIn(viewModelScope)
        setState(BaseUiState.Data(HomeState(listTodoAdded = flowData)))
    }

    private fun addNewTodo(todo: TodoDto) = safeLaunch {
        val params = AddTodo.Params(todo)
        call(addTodo(params)){}
    }
}