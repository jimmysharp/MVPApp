package com.example.mvpapp.ui.tasklist

import android.content.Context
import com.example.mvpapp.Injection

object TaskListInjection {
    fun providePresenter(view: TaskListContract.View): TaskListContract.Presenter {
        return TaskListPresenter(
            view,
            Injection.provideTasksDataSource())
    }
}