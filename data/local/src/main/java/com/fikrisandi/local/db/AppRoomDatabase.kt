package com.fikrisandi.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.fikrisandi.local.dao.TodoDao
import com.fikrisandi.model.local.todo.TodoEntity

@Database(
    entities = [TodoEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppRoomDatabase : RoomDatabase() {
    abstract fun getTodoDao(): TodoDao
}