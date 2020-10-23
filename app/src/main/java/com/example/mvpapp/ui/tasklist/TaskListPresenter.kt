package com.example.mvpapp.ui.tasklist

import com.example.mvpapp.data.Result
import com.example.mvpapp.data.Task
import com.example.mvpapp.data.source.TasksDataSource

class TaskListPresenter(
    private val view: TaskListContract.View,
    private val dataSource: TasksDataSource
): TaskListContract.Presenter {

    // Fragmentが稼働しているかどうかのフラグ
    private var isActivated: Boolean = false

    override fun start() {
        view.hideLoadingIndicator()
        loadTasks()

        isActivated = true
    }

    override fun stop() {
        isActivated = false
    }

    override fun loadTasks() {
        view.showLoadingIndicator()

        dataSource.getAllTasks({ result ->
            if(isActivated) {
                when (result) {
                    is Result.Success -> view.showTasks(result.data)
                    is Result.Failure -> view.showError()
                }

                view.hideLoadingIndicator()
            }
        })
    }

    override fun openTaskDetail(task: Task) {
        view.navigateTaskDetail(task.id)
    }

    override fun addTask() {
        view.navigateAddNewTask()
    }

    override fun deleteAllTasks() {
        view.showLoadingIndicator()

        dataSource.deleteAllTasks({ result ->
            if(isActivated) {
                when (result) {
                    is Result.Success -> {
                        view.hideLoadingIndicator()
                        loadTasks()
                    }
                    is Result.Failure -> {
                        view.showError()
                        view.hideLoadingIndicator()
                    }
                }
            }
        })
    }
}
