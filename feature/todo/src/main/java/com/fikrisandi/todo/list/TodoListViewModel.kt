package com.fikrisandi.todo.list

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.fikrisandi.domain.todo.AddTodo
import com.fikrisandi.domain.todo.GetListPagingTodo
import com.fikrisandi.framework.base.BaseUiState
import com.fikrisandi.framework.base.MvviViewModel
import com.fikrisandi.model.dto.todo.TodoDto
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import javax.inject.Inject

@HiltViewModel
class TodoListViewModel @Inject constructor (private val getListPagingTodo: GetListPagingTodo, private val addTodo: AddTodo): MvviViewModel<BaseUiState<TodoListState>, TodoListEvent>() {

    private val mConfig = PagingConfig(initialLoadSize = 10, prefetchDistance = 10, pageSize = 10)

    var onAddedEvent by mutableStateOf(false)

    override fun onTrigger(eventType: TodoListEvent) {
        when(eventType){
            is TodoListEvent.LoadTodoLastAdded -> loadTodoLastAdded()
            is TodoListEvent.LoadTodoCompleted -> loadTodoCompleted()
            is TodoListEvent.CreateNewTodo -> addNewTodo(eventType.todo)
        }
    }


    private fun loadTodoLastAdded() = safeLaunch {
        startLoading()
        delay(1500)
        val params = GetListPagingTodo.Params(
            pagingConfig = mConfig,
            options = mapOf(
                "lastAdded" to true
            )
        )

        val pagingData = getListPagingTodo(params).cachedIn(viewModelScope)
        setState(BaseUiState.Data(TodoListState(pagedData = pagingData)))
    }

    private fun loadTodoCompleted() = safeLaunch {
        startLoading()
        delay(1500)
        val params = GetListPagingTodo.Params(
            pagingConfig = mConfig,
            options = mapOf(
                "isCompleted" to true
            )
        )
        val pagingData = getListPagingTodo(params).cachedIn(viewModelScope)
        setState(BaseUiState.Data(TodoListState(pagedData = pagingData)))
    }

    private fun addNewTodo(todo: TodoDto) = safeLaunch {
        val params = AddTodo.Params(todo)
        call(addTodo(params)){
            onAddedEvent  = true
        }
    }


}