package com.fikrisandi.model.dto.todo

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TodoDto(
    val id: Int = 0,
    val title: String = "",
    val description: String = "",
    val dueDate: Long = 0L
): Parcelable{
    fun dueDateNotSelected() = dueDate == 0L
}
