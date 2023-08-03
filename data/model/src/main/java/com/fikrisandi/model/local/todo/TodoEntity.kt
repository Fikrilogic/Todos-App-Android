package com.fikrisandi.model.local.todo

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = TodoEntity.NAME)
data class TodoEntity(
    @PrimaryKey(autoGenerate = true) var id: Int,
    @ColumnInfo(name = "title") var title: String,
    @ColumnInfo(name = "description") var description: String,
    @ColumnInfo(name = "due_date") var dueDate: Long,
){
    companion object{
        const val NAME = "Todo"
    }
}
