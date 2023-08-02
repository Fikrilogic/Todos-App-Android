package com.fikrisandi.local.di

import android.content.Context
import androidx.room.Room
import com.fikrisandi.local.BuildConfig
import com.fikrisandi.local.dao.TodoDao
import com.fikrisandi.local.db.AppRoomDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LocalModule {
    companion object {
        const val DB_NAME = "dbname"
    }

    @Provides
    @Singleton
    @Named(DB_NAME)
    fun provideDatabaseName(): String {
        return BuildConfig.DB_NAME
    }

    @Provides
    @Singleton
    fun provideDatabase(
        @Named(DB_NAME) dbName: String, @ApplicationContext context: Context
    ): AppRoomDatabase {
        return Room.databaseBuilder(context, AppRoomDatabase::class.java, dbName)
            .fallbackToDestructiveMigration().build()
    }

    @Provides
    @Singleton
    fun provideTodoDao(appRoomDatabase: AppRoomDatabase): TodoDao{
        return appRoomDatabase.getTodoDao()
    }
}