package com.example.mvpapp.data.source

import com.example.mvpapp.data.Task
import retrofit2.Call
import retrofit2.http.*

interface TodoApi {
    @GET("{userId}/tasks")
    fun getAllTasks(@Path("userId") userId: String): Call<List<Task>>

    @GET("{userId}/tasks/{taskId}")
    fun getTask(@Path("userId") userId: String, @Path("taskId") taskId: String): Call<Task>

    @Headers("Content-Type:application/json")
    @POST("{userId}/tasks")
    fun createTask(@Path("userId") userId: String, @Body task: TaskRequest): Call<Task>

    @Headers("Content-Type:application/json")
    @PUT("{userId}/tasks/{taskId}")
    fun updateTask(@Path("userId") userId: String, @Path("taskId") taskId: String, @Body task: Task): Call<Unit>

    @DELETE("{userId}/tasks")
    fun deleteAllTasks(@Path("userId") userId: String): Call<Unit>
}