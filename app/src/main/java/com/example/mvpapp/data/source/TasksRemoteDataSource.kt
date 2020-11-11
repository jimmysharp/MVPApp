package com.example.mvpapp.data.source

import com.example.mvpapp.data.Task
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.IllegalArgumentException

class TasksRemoteDataSource(
    private val todoApi: TodoApi
): TasksDataSource {

    companion object {
        // ユーザID
        private const val USER_ID = "hoge"
    }

    override fun getAllTasks(callback: TasksDataSource.GetAllTasksCallback) {
        todoApi.getAllTasks(USER_ID).enqueue(object : Callback<List<Task>>{
            override fun onResponse(call: Call<List<Task>>, response: Response<List<Task>>) {
                val body = response.body()

                if(response.isSuccessful && body != null){
                    callback.onSuccess(body)
                }
                else {
                    callback.onError(IllegalArgumentException("Failed to parse response"))
                }
            }

            override fun onFailure(call: Call<List<Task>>, t: Throwable) {
                callback.onError(t)
            }
        })
    }

    override fun getTask(taskId: String, callback: TasksDataSource.GetTaskCallback) {
        // TODO 演習2 : タスク取得処理
        /*
        以下の処理が必要

        - タスクIDを元にタスク取得APIを呼び出す
        - 呼び出し後の処理で以下を行う
          - 通信成功時
            - レスポンスパースに成功し、ボディを取得できた場合
              - コールバックのonSuccessを呼び、成功処理を行わせる
            - レスポンスパースに失敗した場合
              - コールバックのonErrorを呼び、失敗処理を行わせる
              - 適切な例外を渡す(今回はIllegalArgumentException)
          - 通信失敗時
            - コールバックのonErrorを呼び、失敗処理を行わせる
         */
    }

    override fun createTask(
        title: String,
        description: String,
        callback: TasksDataSource.CreateTaskCallback
    ) {
        todoApi.createTask(USER_ID, TaskRequest(title, description)).enqueue(object : Callback<Task>{
            override fun onResponse(call: Call<Task>, response: Response<Task>) {
                if(response.isSuccessful){
                    callback.onSuccess()
                }
                else {
                    callback.onError(IllegalArgumentException("Failed to parse response"))
                }
            }

            override fun onFailure(call: Call<Task>, t: Throwable) {
                callback.onError(t)
            }
        })
    }

    override fun updateTask(task: Task, callback: TasksDataSource.UpdateTaskCallback) {
        todoApi.updateTask(USER_ID, task.id, task).enqueue(object : Callback<Unit>{
            override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                if(response.isSuccessful){
                    callback.onSuccess()
                }
                else {
                    callback.onError(IllegalArgumentException("Failed to parse response"))
                }
            }

            override fun onFailure(call: Call<Unit>, t: Throwable) {
                callback.onError(t)
            }
        })
    }

    override fun deleteAllTasks(callback: TasksDataSource.DeleteAllTasksCallback) {
        todoApi.deleteAllTasks(USER_ID).enqueue(object : Callback<Unit>{
            override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                if(response.isSuccessful){
                    callback.onSuccess()
                }
                else {
                    callback.onError(IllegalArgumentException("Failed to parse response"))
                }
            }

            override fun onFailure(call: Call<Unit>, t: Throwable) {
                callback.onError(t)
            }
        })
    }
}
