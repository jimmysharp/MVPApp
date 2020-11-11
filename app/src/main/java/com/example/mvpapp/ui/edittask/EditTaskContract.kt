package com.example.mvpapp.ui.edittask

import com.example.mvpapp.data.Task

interface EditTaskContract {
    interface View {
        // 設定済みのタスク詳細を表示
        fun setTaskDetail(task: Task)
        // 実行エラー表示
        // 本来はエラー種別ごとにメッセージを用意すべきだが、今回は1つだけ
        fun showError()

        // 読み込み中表示のON/OFF
        fun showLoadingIndicator()
        fun hideLoadingIndicator()

        // 別画面への移動
        fun navigateFinishEditTask()
    }

    interface Presenter {
        // 開始・終了処理
        fun start(taskId: String?)
        fun stop()

        // タスクの保存
        fun saveTask(title: String, description: String)
    }
}