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
        // TODO 演習1 : 画面スタート時の処理
        /*
        以下の処理が必要

        - 受け取ったタスクIDを保存
        - 稼働中フラグをtrueに
        - ロード中表示を出す
        - タスクIDを元に、タスクを取得
          - 成功の場合
            - ロード中表示を消す
            - 取得したタスクを表示
          - 失敗の場合
            - ロード中表示を消す
            - 取得したタスクを表示
          - 処理完了時に画面が消えている可能性があるので、稼働中かを判別する必要あり
         */
    }

    override fun stop() {
        // TODO 演習1 : 画面ストップ時の処理
        /*
        以下の処理が必要

        - 稼働中フラグをfalseに
         */
    }

    override fun editTask() {
        // TODO 演習1 : タスク編集を呼び出された場合の処理
        /*
        以下の処理が必要

        - タスク編集画面へ遷移
         */
    }
}