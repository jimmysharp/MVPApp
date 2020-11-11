package com.example.mvpapp

import android.content.Context
import com.example.mvpapp.data.source.TasksDataSource
import com.example.mvpapp.data.source.TasksRemoteDataSource
import com.example.mvpapp.data.source.TodoApiInjection

object Injection {

    private val tasksDataSource = TasksRemoteDataSource(TodoApiInjection.provideTodoApi())

    fun provideTasksDataSource(): TasksDataSource {
        return tasksDataSource
    }
}