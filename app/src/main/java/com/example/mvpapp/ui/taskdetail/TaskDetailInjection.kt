package com.example.mvpapp.ui.taskdetail

import android.content.Context
import com.example.mvpapp.Injection

object TaskDetailInjection {
    fun providePresenter(view: TaskDetailContract.View): TaskDetailContract.Presenter {
        return TaskDetailPresenter(view, Injection.provideTasksDataSource())
    }
}