package com.example.mvpapp.ui.taskdetail

import com.example.mvpapp.data.Task

interface TaskDetailContract {
    interface View {
        // タスク詳細の表示
        fun showTaskDetail(task: Task)
        // 実行エラー表示
        // 本来はエラー種別ごとにメッセージを用意すべきだが、今回は1つだけ
        fun showError()

        // 読み込み中表示のON/OFF
        fun showLoadingIndicator()
        fun hideLoadingIndicator()

        // 別画面への移動
        fun navigateEditTask(taskId: String)
    }

    interface Presenter {
        // 開始・終了処理
        fun start(taskId: String)
        fun stop()

        // タスクの編集
        fun editTask()
    }
}