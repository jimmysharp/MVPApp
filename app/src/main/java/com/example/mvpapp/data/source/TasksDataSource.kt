package com.example.mvpapp.data.source

import com.example.mvpapp.data.Task

interface TasksDataSource {

    interface GetAllTasksCallback {
        fun onSuccess(tasks: List<Task>)
        fun onError(t: Throwable)
    }

    interface GetTaskCallback {
        fun onSuccess(task: Task)
        fun onError(t: Throwable)
    }

    interface CreateTaskCallback {
        fun onSuccess()
        fun onError(t: Throwable)
    }

    interface UpdateTaskCallback {
        fun onSuccess()
        fun onError(t: Throwable)
    }

    interface DeleteAllTasksCallback {
        fun onSuccess()
        fun onError(t: Throwable)
    }

    // タスク全件取得
    fun getAllTasks(callback: GetAllTasksCallback)

    // タスク1件の取得
    fun getTask(taskId: String, callback: GetTaskCallback)

    // タスク新規作成
    fun createTask(title: String, description: String, callback: CreateTaskCallback)

    // タスク更新
    fun updateTask(task: Task, callback: UpdateTaskCallback)

    // タスク全件削除
    fun deleteAllTasks(callback: DeleteAllTasksCallback)
}