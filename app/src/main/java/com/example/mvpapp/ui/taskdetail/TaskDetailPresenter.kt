package com.example.mvpapp.ui.taskdetail

import com.example.mvpapp.data.Result
import com.example.mvpapp.data.source.TasksDataSource

class TaskDetailPresenter(
    private val view: TaskDetailContract.View,
    private val dataSource: TasksDataSource
): TaskDetailContract.Presenter {

    private lateinit var taskId: String

    override fun start(taskId: String) {
        view.showNoData()
        view.showLoadingIndicator()

        this.taskId = taskId
        dataSource.getTask(taskId, { result ->
            when(result) {
                is Result.Success -> view.showTaskDetail(result.data)
                is Result.Error -> view.showError()
            }
            view.HideLoadingIndicator()
        })
    }

    override fun editTask() {
        view.navigateEditTask(taskId)
    }
}