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

        // 最初にタスクを読み込む
        loadTasks()

        isActivated = true
    }

    override fun stop() {
        isActivated = false
    }

    override fun loadTasks() {
        // ロード中表示を出す
        view.showLoadingIndicator()

        // タスク取得開始
        dataSource.getAllTasks(object : TasksDataSource.GetAllTasksCallback{
            override fun onSuccess(tasks: List<Task>) {
                // 成功したらタスクを表示し、ロード中表示を消す
                view.hideLoadingIndicator()
                view.showTasks(tasks)
            }

            override fun onError(t: Throwable) {
                // 失敗したらエラー表示し、ロード中表示を消す
                view.hideLoadingIndicator()
                view.showError()
            }

        })
    }

    override fun openTaskDetail(task: Task) {
        view.navigateTaskDetail(task.taskId)
    }

    override fun addTask() {
        view.navigateAddNewTask()
    }

    override fun deleteAllTasks() {
        // ロード中表示を出す
        view.showLoadingIndicator()

        // タスク削除開始
        dataSource.deleteAllTasks(object : TasksDataSource.DeleteAllTasksCallback{
            override fun onSuccess() {
                // 成功したらタスクを表示し、ロード中表示を消す
                view.hideLoadingIndicator()
                loadTasks()
            }

            override fun onError(t: Throwable) {
                // 失敗したらエラー表示し、ロード中表示を消す
                view.hideLoadingIndicator()
                view.showError()
            }

        })
    }
}
