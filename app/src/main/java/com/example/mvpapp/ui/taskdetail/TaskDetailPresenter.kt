package com.example.mvpapp.ui.taskdetail

import com.example.mvpapp.data.Task
import com.example.mvpapp.data.source.TasksDataSource

class TaskDetailPresenter(
    private val view: TaskDetailContract.View,
    private val dataSource: TasksDataSource
): TaskDetailContract.Presenter {

    // Fragment稼働中かのフラグ
    private var isActivated: Boolean = false
    // 表示中タスクのID
    private lateinit var taskId: String

    override fun start(taskId: String) {
        // フラグ更新
        isActivated = true
        // タスクIDを保存
        this.taskId = taskId

        // データ取得開始
        view.showLoadingIndicator()
        dataSource.getTask(taskId, object : TasksDataSource.GetTaskCallback{
            override fun onSuccess(task: Task) {
                if(isActivated) {
                    // データ取得できた場合は表示
                    view.hideLoadingIndicator()
                    view.showTaskDetail(task)
                }
            }

            override fun onError(t: Throwable) {
                if(isActivated) {
                    // 取得失敗した場合はエラー表示
                    view.hideLoadingIndicator()
                    view.showError()
                }
            }

        })
    }

    override fun stop() {
        // フラグ更新
        isActivated = false
    }

    override fun editTask() {
        // Viewで遷移してもらう
        view.navigateEditTask(taskId)
    }
}