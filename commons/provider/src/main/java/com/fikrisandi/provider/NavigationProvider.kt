package com.fikrisandi.provider

import android.os.Parcelable
import com.fikrisandi.model.dto.todo.TodoDto
import kotlinx.parcelize.Parcelize

interface NavigationProvider {

    fun navigateUp()
    fun openTodoList(flag: TodoFlagType)
    fun openTodoDetail(state: TodoDto)

}

class EmptyNavigationProvider: NavigationProvider{
    override fun navigateUp() {
    }

    override fun openTodoList(flag: TodoFlagType) {
    }

    override fun openTodoDetail(state: TodoDto) {
    }

}

@Parcelize
enum class TodoFlagType(val stringFlag: String) : Parcelable {
    ON_PROGRESS("isLastAdded"),
    COMPLETED("isCompleted")
}