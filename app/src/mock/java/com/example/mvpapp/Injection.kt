package com.example.mvpapp

import android.content.Context
import com.example.mvpapp.data.source.TasksDataSource
import com.example.mvpapp.data.source.TasksMockDataSource

object Injection {

    private val tasksDataSource = TasksMockDataSource()

    fun provideTasksDataSource(): TasksDataSource {
        return tasksDataSource
    }
}