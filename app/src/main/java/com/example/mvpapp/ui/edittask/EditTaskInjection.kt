package com.example.mvpapp.ui.edittask

import android.content.Context
import com.example.mvpapp.Injection

object EditTaskInjection {
    fun providePresenter(
        view: EditTaskContract.View
    ): EditTaskContract.Presenter {
        return EditTaskPresenter(view, Injection.provideTasksDataSource())
    }
}