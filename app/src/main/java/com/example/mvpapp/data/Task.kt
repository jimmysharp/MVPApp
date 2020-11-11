package com.example.mvpapp.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.util.*

// タスクオブジェクト
@JsonClass(generateAdapter = true)
data class Task(
    @Json(name="task_id") val taskId: String,
    val title: String,
    val description: String
)