package com.example.mvpapp.data.source

import com.example.mvpapp.data.Task
import retrofit2.Call
import retrofit2.http.*

interface TodoApi {
    @GET("tasks")
    fun getAllTasks(): Call<List<Task>>

    @GET("tasks/{taskId}")
    fun getTask(@Path("taskId") taskId: String): Call<Task>

    @Headers("Content-Type:application/json")
    @POST("tasks")
    fun createTask(@Body task: TaskRequest): Call<Task>

    @Headers("Content-Type:application/json")
    @PUT("tasks/{taskId}")
    fun updateTask(@Path("taskId") taskId: String, @Body task: Task): Call<Unit>

    @DELETE("tasks")
    fun deleteAllTasks(): Call<Unit>
}