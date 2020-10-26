package com.example.mvpapp.ui.tasklist

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

        dataSource.getAllTasks(object : TasksDataSource.GetAllTasksCallback{
            override fun onSuccess(tasks: List<Task>) {
                view.showTasks(tasks)
                view.hideLoadingIndicator()
            }

            override fun onError(t: Throwable) {
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

        dataSource.deleteAllTasks(object : TasksDataSource.DeleteAllTasksCallback{
            override fun onSuccess() {
                view.hideLoadingIndicator()
                loadTasks()
            }

            override fun onError(t: Throwable) {
                view.hideLoadingIndicator()
                view.showError()
            }

        })
    }
}
