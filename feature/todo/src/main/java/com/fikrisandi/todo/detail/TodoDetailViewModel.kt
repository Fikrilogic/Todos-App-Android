package com.fikrisandi.todo.detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.fikrisandi.domain.todo.DeleteTodo
import com.fikrisandi.framework.base.MvvmViewModel
import com.fikrisandi.model.dto.todo.TodoDto
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TodoDetailViewModel @Inject constructor(private val deleteTodo: DeleteTodo): MvvmViewModel() {


    var loading by mutableStateOf(false)
        private set

    var alert by mutableStateOf(Pair(false, ""))
        private set

    override fun startLoading() {
        super.startLoading()
        loading = true
    }

    fun endLoading(){
        loading = false
    }

    override fun handleError(exception: Throwable) {
        super.handleError(exception)
        alert = Pair(true, exception.localizedMessage)
    }

    fun showMessage(message: String){
        alert = Pair(true, message)
    }

    fun hideAlert() {alert = Pair(false, "")}


    fun deleteData(todo: TodoDto, callback: (message: String)-> Unit = {}) = safeLaunch {
        val params = DeleteTodo.Params(todo)
        call(
            deleteTodo(params)
        ){
            callback.invoke("Success Delete Todo")
        }
    }

}