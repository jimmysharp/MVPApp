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

    fun getAllTasks(callback: GetAllTasksCallback)

    fun getTask(taskId: String, callback: GetTaskCallback)

    fun createTask(title: String, description: String, callback: CreateTaskCallback)

    fun updateTask(task: Task, callback: UpdateTaskCallback)

    fun deleteAllTasks(callback: DeleteAllTasksCallback)
}