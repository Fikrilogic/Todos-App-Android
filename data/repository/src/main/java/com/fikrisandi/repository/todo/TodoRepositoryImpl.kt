package com.fikrisandi.repository.todo

import com.fikrisandi.local.dao.TodoDao
import com.fikrisandi.model.local.todo.TodoEntity
import javax.inject.Inject


class TodoRepositoryImpl @Inject constructor(val dao: TodoDao): TodoRepository {

    override suspend fun get(limit: Int, offset: Int): List<TodoEntity> {
        return dao.get(limit, offset)
    }

    override suspend fun getById(id: Int): TodoEntity? {
        return dao.getById(id)
    }

    override suspend fun insert(data: TodoEntity) {
        dao.insert(data)
    }

    override suspend fun insert(data: List<TodoEntity>) {
        dao.insert(data)
    }

    override suspend fun update(data: TodoEntity) {
        dao.update(data)
    }

    override suspend fun delete(data: TodoEntity) {
        dao.delete(data)
    }
}