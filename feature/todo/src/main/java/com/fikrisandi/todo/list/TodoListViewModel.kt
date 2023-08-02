package com.fikrisandi.todo.list

import com.fikrisandi.framework.base.BaseUiState
import com.fikrisandi.framework.base.MvviViewModel

class TodoListViewModel: MvviViewModel<BaseUiState<TodoListState>, TodoListEvent>() {
    override fun onTrigger(eventType: TodoListEvent) {
//        when(eventType){
//
//        }
    }
}