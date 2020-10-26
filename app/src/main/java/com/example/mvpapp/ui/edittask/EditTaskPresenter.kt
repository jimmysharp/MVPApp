package com.example.mvpapp.ui.edittask

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

        // タスクIDを指定して呼び出された場合(更新処理の場合)
        // 既存タスクのデータを読み込む
        if (taskId != null) {
            view.showLoadingIndicator()

            dataSource.getTask(taskId, object : TasksDataSource.GetTaskCallback{
                override fun onSuccess(task: Task) {
                    if(isActivated) {
                        // タスク取得できた場合は表示
                        view.hideLoadingIndicator()
                        view.setTaskDetail(task)
                    }
                }

                override fun onError(t: Throwable) {
                    if(isActivated) {
                        // 取得できなかった場合はエラー表示
                        view.hideLoadingIndicator()
                        view.showError()
                    }
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
            // 既存タスク編集の場合、更新処理
            val task = Task(currentTaskId, title, description)
            dataSource.updateTask(task, object : TasksDataSource.UpdateTaskCallback{
                override fun onSuccess() {
                    if(isActivated) {
                        // 更新できた場合はタスク一覧画面へ戻る
                        view.hideLoadingIndicator()
                        view.navigateFinishEditTask()
                    }
                }

                override fun onError(t: Throwable) {
                    if(isActivated) {
                        // 失敗した場合はエラー表示
                        view.hideLoadingIndicator()
                        view.showError()
                    }
                }

            })
        } else {
            // 新規タスク編集の場合、作成処理
            dataSource.createTask(title, description, object : TasksDataSource.CreateTaskCallback{
                override fun onSuccess() {
                    if(isActivated) {
                        // 作成できた場合はタスク一覧画面へ戻る
                        view.hideLoadingIndicator()
                        view.navigateFinishEditTask()
                    }
                }

                override fun onError(t: Throwable) {
                    if(isActivated) {
                        // 失敗した場合はエラー表示
                        view.hideLoadingIndicator()
                        view.showError()
                    }
                }

            })
        }
    }
}