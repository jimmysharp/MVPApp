package com.example.mvpapp.data

// データ取得の「成功」か「失敗」のどちらかの値をとるクラス
// 成功なら取得したデータを、失敗ならエラー(Throwable)になる
sealed class Result<out T> {
    data class Success<out T>(val data: T): Result<T>()
    data class Failure(val error: Throwable): Result<Nothing>()
}
