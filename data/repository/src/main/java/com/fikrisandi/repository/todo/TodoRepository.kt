package com.fikrisandi.repository.todo

import com.fikrisandi.model.local.todo.TodoEntity

interface TodoRepository {
    suspend fun get(limit: Int, offset: Int): List<TodoEntity>
    suspend fun getById(id: Int): TodoEntity?
    suspend fun insert(data: TodoEntity)
    suspend fun insert(data: List<TodoEntity>)
    suspend fun update(data: TodoEntity)
    suspend fun delete(data: TodoEntity)
}