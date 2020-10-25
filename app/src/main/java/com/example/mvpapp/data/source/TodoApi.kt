package com.example.mvpapp.data.source

import com.example.mvpapp.data.Task
import retrofit2.Call
import retrofit2.http.*

interface TodoApi {
    // タスク全件取得
    @GET("{userId}/tasks")
    fun getAllTasks(@Path("userId") userId: String): Call<List<Task>>

    // タスク取得
    @GET("{userId}/tasks/{taskId}")
    fun getTask(@Path("userId") userId: String, @Path("taskId") taskId: String): Call<Task>

    // タスク新規作成
    @Headers("Content-Type:application/json")
    @POST("{userId}/tasks")
    fun createTask(@Path("userId") userId: String, @Body task: TaskRequest): Call<Task>

    // タスク更新
    @Headers("Content-Type:application/json")
    @PUT("{userId}/tasks/{taskId}")
    fun updateTask(@Path("userId") userId: String, @Path("taskId") taskId: String, @Body task: Task): Call<Unit>

    // タスク全件削除
    @DELETE("{userId}/tasks")
    fun deleteAllTasks(@Path("userId") userId: String): Call<Unit>
}