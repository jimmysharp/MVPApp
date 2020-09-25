package com.example.mvpapp.ui.taskdetail

import com.example.mvpapp.data.Task

interface TaskDetailContract {
    interface View {
        fun showTaskDetail(task: Task)
        fun showNoData()
        fun showError()

        fun showLoadingIndicator()
        fun HideLoadingIndicator()

        fun navigateEditTask(taskId: String)
    }

    interface Presenter {
        fun start(taskId: String)

        fun editTask()
    }
}