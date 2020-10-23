package com.example.mvpapp.ui.tasklist

import android.content.Context
import com.example.mvpapp.data.source.Injection

object TaskListInjection {
    fun providePresenter(view: TaskListContract.View, context: Context): TaskListContract.Presenter {
        return TaskListPresenter(
            view,
            Injection.provideTasksDataSource(context))
    }
}