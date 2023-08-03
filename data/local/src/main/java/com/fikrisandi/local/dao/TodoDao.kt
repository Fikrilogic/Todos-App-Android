package com.fikrisandi.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.fikrisandi.framework.room.BaseDao
import com.fikrisandi.model.local.todo.TodoEntity

@Dao
interface TodoDao: BaseDao<TodoEntity> {

    @Query("SELECT * FROM ${TodoEntity.NAME}")
    suspend fun get(): List<TodoEntity>

    @Query("SELECT * FROM ${TodoEntity.NAME} LIMIT :limit OFFSET :offset")
    suspend fun get(limit: Int, offset: Int): List<TodoEntity>

    @Query("SELECT * FROM ${TodoEntity.NAME} WHERE id = :id")
    suspend fun getById(id: Int): TodoEntity?

    @Query("SELECT * FROM ${TodoEntity.NAME} WHERE due_date >= :date  LIMIT :limit OFFSET :offset")
    suspend fun getIsCompleted(limit: Int, offset: Int, date: Long): List<TodoEntity>
    @Query("SELECT * FROM ${TodoEntity.NAME} WHERE due_date >= :date")
    suspend fun getIsCompleted(date: Long): List<TodoEntity>

    @Query("SELECT * FROM ${TodoEntity.NAME} WHERE due_date < :date ORDER BY due_date ASC  LIMIT :limit OFFSET :offset")
    suspend fun getLastAdded(limit: Int, offset: Int, date: Long): List<TodoEntity>

}