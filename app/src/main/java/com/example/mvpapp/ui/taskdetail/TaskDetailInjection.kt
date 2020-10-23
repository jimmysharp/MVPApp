package com.example.mvpapp.ui.taskdetail

import android.content.Context
import com.example.mvpapp.data.source.Injection

object TaskDetailInjection {
    fun providePresenter(view: TaskDetailContract.View ,context: Context): TaskDetailContract.Presenter {
        return TaskDetailPresenter(view, Injection.provideTasksDataSource(context))
    }
}