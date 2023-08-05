package com.fikrisandi.todo.detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.fikrisandi.domain.todo.DeleteTodo
import com.fikrisandi.domain.todo.UpdateTodo
import com.fikrisandi.framework.base.MvvmViewModel
import com.fikrisandi.model.dto.todo.TodoDto
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TodoDetailViewModel @Inject constructor(private val deleteTodo: DeleteTodo, private val updateTodo: UpdateTodo): MvvmViewModel() {


    var loading by mutableStateOf(false)
        private set

    var alert by mutableStateOf(Pair(false, ""))
        private set

    override fun startLoading() {
        super.startLoading()
        loading = true
    }

    override fun handleError(exception: Throwable) {
        super.handleError(exception)
        alert = Pair(true, exception.localizedMessage.orEmpty())
    }

    fun deleteData(todo: TodoDto, callback: (message: String)-> Unit = {}) = safeLaunch {
        val params = DeleteTodo.Params(todo)
        call(
            deleteTodo(params)
        ){
            callback.invoke("Success Delete Todo")
        }
    }

    fun updateData(todo: TodoDto, callback: (message: String)-> Unit = {}) = safeLaunch {
        val params = UpdateTodo.Params(todo)
        call(
            updateTodo(params)
        ){
            callback.invoke("Success Update Todo")
        }
    }

}