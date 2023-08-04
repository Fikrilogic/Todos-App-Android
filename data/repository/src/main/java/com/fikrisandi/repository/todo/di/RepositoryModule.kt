package com.fikrisandi.repository.todo.di

import com.fikrisandi.local.dao.TodoDao
import com.fikrisandi.repository.todo.TodoRepository
import com.fikrisandi.repository.todo.TodoRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun providesTodoRepository(dao: TodoDao): TodoRepository = TodoRepositoryImpl(dao)
}