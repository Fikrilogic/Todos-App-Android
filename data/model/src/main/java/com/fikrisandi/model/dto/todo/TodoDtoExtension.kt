package com.fikrisandi.model.dto.todo

import com.fikrisandi.model.local.todo.TodoEntity

fun TodoEntity.toDto() = TodoDto(
    id = this.id,
    title = this.title,
    description = this.description,
    dueDate = this.dueDate
)

fun TodoDto.toEntity() = TodoEntity(
    id = this.id,
    title = this.title,
    description = this.description,
    dueDate = this.dueDate,
)