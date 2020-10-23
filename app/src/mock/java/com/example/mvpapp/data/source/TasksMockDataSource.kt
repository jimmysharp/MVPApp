package com.example.mvpapp.data.source

import android.os.Handler
import android.os.Looper
import com.example.mvpapp.data.Result
import com.example.mvpapp.data.Task
import java.util.*

class TasksMockDataSource() : TasksDataSource{

    // ダミーのネットワーク遅延
    private val FAKE_NETWORK_DELAY_MILLIS = 1000L

    // 仮のデータ保存オブジェクト
    // サーバにデータを保存する代わりに、辞書型オブジェクトに値を保存する
    private val tasks = mutableMapOf<String, Task>()

    override fun getAllTasks(callback: TasksDataSource.GetAllTasksCallback) {
        // 意図せず変更されないよう、一度値をコピーしてから返す
        val copy = tasks.values.toList()

        // 遅延を入れてコールバックを呼び出す
        Handler(Looper.getMainLooper()).postDelayed({
            callback.onFinished(Result.Success(copy))
        }, FAKE_NETWORK_DELAY_MILLIS)
    }

    override fun getTask(taskId: String, callback: TasksDataSource.GetTaskCallback) {
        val task = tasks[taskId]

        // Nullチェック
        if (task != null) {
            Handler(Looper.getMainLooper()).postDelayed({
                callback.onFinished(Result.Success(task))
            }, FAKE_NETWORK_DELAY_MILLIS)
        } else {
            Handler(Looper.getMainLooper()).postDelayed({
                callback.onFinished(Result.Failure(NoSuchElementException("Task ID $taskId was not found.")))
            }, FAKE_NETWORK_DELAY_MILLIS)
        }
    }

    override fun createTask(
        title: String,
        description: String,
        callback: TasksDataSource.CreateTaskCallback
    ) {
        val task = Task(
            UUID.randomUUID().toString(),
            title,
            description
        )
        tasks[task.id] = task

        Handler(Looper.getMainLooper()).postDelayed({
            callback.onFinished(Result.Success(Unit))
        }, FAKE_NETWORK_DELAY_MILLIS)
    }

    override fun updateTask(task: Task, callback: TasksDataSource.UpdateTaskCallback) {
        tasks[task.id] = task

        Handler(Looper.getMainLooper()).postDelayed({
            callback.onFinished(Result.Success(Unit))
        }, FAKE_NETWORK_DELAY_MILLIS)
    }

    override fun deleteAllTasks(callback: TasksDataSource.DeleteAllTasksCallback) {
        tasks.clear()

        Handler(Looper.getMainLooper()).postDelayed({
            callback.onFinished(Result.Success(Unit))
        }, FAKE_NETWORK_DELAY_MILLIS)
    }
}