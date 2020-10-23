package com.example.mvpapp.ui.edittask

import com.example.mvpapp.data.Result
import com.example.mvpapp.data.Task
import com.example.mvpapp.data.source.TasksDataSource

class EditTaskPresenter(
    private val view: EditTaskContract.View,
    private val dataSource: TasksDataSource
): EditTaskContract.Presenter {

    // Fragment稼働中かのフラグ
    private var isActivated: Boolean = false
    // 表示中タスクのID
    private var taskId: String? = null

    override fun start(taskId: String?) {
        this.taskId = taskId
        isActivated = true

        view.hideLoadingIndicator()

        if (taskId != null) {
            view.showLoadingIndicator()

            dataSource.getTask(taskId, { result ->
                if(isActivated) {
                    when (result) {
                        is Result.Success -> view.setTaskDetail(result.data)
                        is Result.Failure -> view.showError()
                    }

                    view.hideLoadingIndicator()
                }
            })
        }
    }

    override fun stop() {
        isActivated = false
    }

    override fun saveTask(title: String, description: String) {
        val currentTaskId = taskId

        view.showLoadingIndicator()

        if (currentTaskId != null) {
            val task = Task(currentTaskId, title, description)
            dataSource.updateTask(task, { result ->
                if(isActivated) {
                    view.hideLoadingIndicator()

                    when (result) {
                        is Result.Success -> view.navigateFinishEditTask()
                        is Result.Failure -> view.showError()
                    }
                }
            })
        } else {
            dataSource.createTask(title, description, { result ->
                if(isActivated) {
                    view.hideLoadingIndicator()

                    when (result) {
                        is Result.Success -> view.navigateFinishEditTask()
                        is Result.Failure -> view.showError()
                    }
                }
            })
        }
    }
}