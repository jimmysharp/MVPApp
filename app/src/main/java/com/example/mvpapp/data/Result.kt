package com.example.mvpapp.data

// 「成功」か「失敗」のどちらかの値をとるクラス
sealed class Result<out T> {
    data class Success<out T>(val data: T): Result<T>()
    data class Failure(val error: Throwable): Result<Nothing>()
}
