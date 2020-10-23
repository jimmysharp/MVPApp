package com.example.mvpapp.ui.edittask

import android.content.Context
import com.example.mvpapp.data.source.Injection

object EditTaskInjection {
    fun providePresenter(
        view: EditTaskContract.View,
        context: Context
    ): EditTaskContract.Presenter {
        return EditTaskPresenter(view, Injection.provideTasksDataSource(context))
    }
}