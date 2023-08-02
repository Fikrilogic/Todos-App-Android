package com.fikrisandi.model.dto.todo

import com.fikrisandi.model.local.todo.TodoEntity
import com.fikrisandi.utils.constant.TimeFormatConstant
import com.fikrisandi.utils.time.TimeUtils

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