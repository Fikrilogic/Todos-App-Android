package com.fikrisandi.repository.todo

import com.fikrisandi.model.local.todo.TodoEntity

interface TodoRepository {
    suspend fun get(): List<TodoEntity>
    suspend fun get(limit: Int, offset: Int): List<TodoEntity>
    suspend fun getLastAdded(limit: Int, offset: Int): List<TodoEntity>
    suspend fun getLastAddedCount(): Int
    suspend fun getLastCompleted(limit: Int, offset: Int): List<TodoEntity>
    suspend fun getLastCompleted(): List<TodoEntity>
    suspend fun getLastCompletedCount(): Int
    suspend fun getById(id: Int): TodoEntity?
    suspend fun insert(data: TodoEntity)
    suspend fun insert(data: List<TodoEntity>)
    suspend fun update(data: TodoEntity)
    suspend fun delete(data: TodoEntity)
}