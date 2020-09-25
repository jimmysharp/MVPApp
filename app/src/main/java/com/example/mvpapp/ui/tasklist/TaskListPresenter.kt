package com.example.mvpapp.ui.tasklist

import com.example.mvpapp.data.Result
import com.example.mvpapp.data.Task
import com.example.mvpapp.data.source.TasksDataSource

class TaskListPresenter(
    private val view: TaskListContract.View,
    private val dataSource: TasksDataSource
): TaskListContract.Presenter {

    override fun start() {
        loadTasks()
    }

    override fun loadTasks() {
        view.showLoadingIndicator()

        dataSource.getAllTasks(){ result ->
            when(result) {
                is Result.Success -> view.showTasks(result.data)
                is Result.Error -> view.showError()
            }

            view.hideLoadingIndicator()
        }
    }

    override fun openTaskDetail(task: Task) {
        view.navigateTaskDetail(task.id)
    }

    override fun addTask() {
        view.navigateAddNewTask()
    }
}
