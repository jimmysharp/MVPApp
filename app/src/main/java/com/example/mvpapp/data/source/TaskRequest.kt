package com.example.mvpapp.data.source

import com.example.mvpapp.data.Task
import com.squareup.moshi.JsonClass

// タスク作成リクエスト用のオブジェクト
// タスク生成前にはIDが無い状態なので、別クラスとして用意する
@JsonClass(generateAdapter = true)
data class TaskRequest(
    val title: String,
    val description: String
) {
    fun toTask(taskId: String): Task {
        return Task(taskId, title, description)
    }

    companion object {
        fun fromTask(task: Task): TaskRequest {
            return TaskRequest(task.title, task.description)
        }
    }
}