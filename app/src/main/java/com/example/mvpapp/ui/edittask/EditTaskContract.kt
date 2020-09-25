package com.example.mvpapp.ui.edittask

import com.example.mvpapp.data.Task

interface EditTaskContract {
    interface View {
        fun setTaskDetail(task: Task)
        fun showError()

        fun showLoadingIndicator()
        fun hideLoadingIndicator()

        fun navigateFinishEditTask()
    }

    interface Presenter {
        fun start(taskId: String?)

        fun saveTask(title: String, description: String)
    }
}