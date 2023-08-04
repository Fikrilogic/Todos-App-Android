package com.fikrisandi.model.dto.todo

data class TodoDto(
    val id: Int = 0,
    val title: String = "",
    val description: String = "",
    val dueDate: Long = 0L
){
    fun dueDateNotSelected() = dueDate == 0L
}
