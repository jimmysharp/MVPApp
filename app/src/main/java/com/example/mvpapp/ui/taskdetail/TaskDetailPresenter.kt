package com.example.mvpapp.ui.taskdetail

import com.example.mvpapp.data.Result
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
        this.taskId = taskId
        isActivated = true

        // 初期画面としてデータのない画面を表示
        view.showNoData()

        // ローディング表示を行い、データ取得開始
        view.showLoadingIndicator()
        dataSource.getTask(taskId, { result ->
            if(isActivated) {
                when (result) {
                    is Result.Success -> view.showTaskDetail(result.data)
                    is Result.Failure -> view.showError()
                }
                view.HideLoadingIndicator()
            }
        })
    }

    override fun stop() {
        isActivated = false
    }

    override fun editTask() {
        view.navigateEditTask(taskId)
    }
}