package com.fikrisandi.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.fikrisandi.framework.room.BaseDao
import com.fikrisandi.model.local.todo.TodoEntity

@Dao
interface TodoDao: BaseDao<TodoEntity> {

    @Query("SELECT * FROM ${TodoEntity.NAME} LIMIT :limit OFFSET :offset")
    suspend fun get(limit: Int, offset: Int): List<TodoEntity>

    @Query("SELECT * FROM ${TodoEntity.NAME} WHERE id = :id")
    suspend fun getById(id: Int): TodoEntity?

}