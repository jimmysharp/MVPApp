package com.example.mvpapp.data

import com.squareup.moshi.JsonClass
import java.util.*

@JsonClass(generateAdapter = true)
data class Task(
    val id: String = UUID.randomUUID().toString(),
    val title: String = "",
    val description: String = ""
)