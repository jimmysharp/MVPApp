package com.example.mvpapp.data.source

import com.example.mvpapp.data.Result
import com.example.mvpapp.data.Task

interface TasksDataSource {

    fun interface GetAllTasksCallback {
        fun onFinished(result: Result<List<Task>>)
    }

    fun interface GetTaskCallback {
        fun onFinished(result: Result<Task>)
    }

    fun interface CreateTaskCallback {
        fun onFinished(result: Result<Unit>)
    }

    fun interface UpdateTaskCallback {
        fun onFinished(result: Result<Unit>)
    }

    fun interface DeleteAllTasksCallback {
        fun onFinished(result: Result<Unit>)
    }

    fun getAllTasks(callback: GetAllTasksCallback)

    fun getTask(taskId: String, callback: GetTaskCallback)

    fun createTask(title: String, description: String, callback: CreateTaskCallback)

    fun updateTask(task: Task, callback: UpdateTaskCallback)

    fun deleteAllTasks(callback: DeleteAllTasksCallback)
}