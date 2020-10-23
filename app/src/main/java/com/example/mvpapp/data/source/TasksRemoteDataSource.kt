package com.example.mvpapp.data.source

import com.example.mvpapp.data.Result
import com.example.mvpapp.data.Task
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.IllegalArgumentException

class TasksRemoteDataSource(
    private val todoApi: TodoApi
): TasksDataSource {

    override fun getAllTasks(callback: TasksDataSource.GetAllTasksCallback) {
        todoApi.getAllTasks().enqueue(object : Callback<List<Task>>{
            override fun onResponse(call: Call<List<Task>>, response: Response<List<Task>>) {
                if(response.isSuccessful){
                    callback.onFinished(Result.Success(response.body()!!))
                }
                else {
                    callback.onFinished(Result.Failure(IllegalArgumentException("Failed to parse response")))
                }
            }

            override fun onFailure(call: Call<List<Task>>, t: Throwable) {
                callback.onFinished(Result.Failure(t))
            }
        })
    }

    override fun getTask(taskId: String, callback: TasksDataSource.GetTaskCallback) {
        todoApi.getTask(taskId).enqueue(object : Callback<Task>{
            override fun onResponse(call: Call<Task>, response: Response<Task>) {
                if(response.isSuccessful){
                    callback.onFinished(Result.Success(response.body()!!))
                }
                else {
                    callback.onFinished(Result.Failure(IllegalArgumentException("Failed to parse response")))
                }
            }

            override fun onFailure(call: Call<Task>, t: Throwable) {
                callback.onFinished(Result.Failure(t))
            }
        })
    }

    override fun createTask(
        title: String,
        description: String,
        callback: TasksDataSource.CreateTaskCallback
    ) {
        todoApi.createTask(TaskRequest(title, description)).enqueue(object : Callback<Task>{
            override fun onResponse(call: Call<Task>, response: Response<Task>) {
                if(response.isSuccessful){
                    callback.onFinished(Result.Success(Unit))
                }
                else {
                    callback.onFinished(Result.Failure(IllegalArgumentException("Failed to parse response")))
                }
            }

            override fun onFailure(call: Call<Task>, t: Throwable) {
                callback.onFinished(Result.Failure(t))
            }
        })
    }

    override fun updateTask(task: Task, callback: TasksDataSource.UpdateTaskCallback) {
        todoApi.updateTask(task.id, task).enqueue(object : Callback<Unit>{
            override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                if(response.isSuccessful){
                    callback.onFinished(Result.Success(Unit))
                }
                else {
                    callback.onFinished(Result.Failure(IllegalArgumentException("Failed to parse response")))
                }
            }

            override fun onFailure(call: Call<Unit>, t: Throwable) {
                callback.onFinished(Result.Failure(t))
            }
        })
    }

    override fun deleteAllTasks(callback: TasksDataSource.DeleteAllTasksCallback) {
        todoApi.deleteAllTasks().enqueue(object : Callback<Unit>{
            override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                if(response.isSuccessful){
                    callback.onFinished(Result.Success(Unit))
                }
                else {
                    callback.onFinished(Result.Failure(IllegalArgumentException("Failed to parse response")))
                }
            }

            override fun onFailure(call: Call<Unit>, t: Throwable) {
                callback.onFinished(Result.Failure(t))
            }
        })
    }
}
