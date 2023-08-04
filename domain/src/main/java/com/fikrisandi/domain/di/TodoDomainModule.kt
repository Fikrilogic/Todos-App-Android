package com.fikrisandi.domain.di

import com.fikrisandi.domain.todo.AddTodo
import com.fikrisandi.domain.todo.DeleteTodo
import com.fikrisandi.domain.todo.GetListPagingTodo
import com.fikrisandi.domain.todo.GetListTodo
import com.fikrisandi.domain.todo.UpdateTodo
import com.fikrisandi.repository.todo.TodoRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class TodoDomainModule {

    @Singleton
    @Provides
    fun provideAddTodo(repository: TodoRepository): AddTodo = AddTodo(repository)

    @Singleton
    @Provides
    fun provideDeleteRepository(repository: TodoRepository): DeleteTodo = DeleteTodo(repository)

    @Singleton
    @Provides
    fun provideGetListPagingTodo(repository: TodoRepository): GetListPagingTodo = GetListPagingTodo(repository)

    @Singleton
    @Provides
    fun provideGetListTodo(repository: TodoRepository): GetListTodo = GetListTodo(repository)

    @Singleton
    @Provides
    fun provideUpdateTodo(repository: TodoRepository): UpdateTodo = UpdateTodo(repository)


}