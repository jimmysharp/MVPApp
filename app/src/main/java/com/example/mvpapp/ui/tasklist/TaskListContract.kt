package com.example.mvpapp.ui.tasklist

import com.example.mvpapp.data.Task

interface TaskListContract {
    interface View {
        // タスク一覧表示
        fun showTasks(tasks: List<Task>)

        // 実行エラー表示
        // 本来はエラー種別ごとにメッセージを用意すべきだが、今回は1つだけ
        fun showError()

        // 読み込み中表示のON/OFF
        fun showLoadingIndicator()
        fun hideLoadingIndicator()

        // 別画面への移動
        fun navigateAddNewTask()
        fun navigateTaskDetail(taskId: String)
    }

    interface Presenter {
        // 開始処理
        fun start()

        // タスクのロード
        fun loadTasks()

        // タスクの編集
        fun openTaskDetail(task: Task)

        // タスク追加
        fun addTask()

        // タスク全削除
        fun deleteAllTasks()
    }
}