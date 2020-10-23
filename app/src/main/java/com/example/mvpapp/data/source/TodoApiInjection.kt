package com.example.mvpapp.data.source

import com.squareup.moshi.Moshi
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

object TodoApiInjection {

    // アクセス先URL
    // PCのlocalhostを利用する場合、10.0.2.2を使う
    private const val API_BASEURL = "https://10.0.2.2:3000/"

    private val retrofit: Retrofit
    private val api: TodoApi

    init {
        // 通信ログ出力を仕込む
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        // HTTPクライアント
        // ロギングとタイムアウトを設定
        val httpClient = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .readTimeout(30, TimeUnit.SECONDS)
            .build()

        // JSONパーサ
        // デフォルト設定で作成
        val moshi = Moshi.Builder()
            .build()

        // Retrofitを初期化
        retrofit = Retrofit.Builder()
            .baseUrl(API_BASEURL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(httpClient)
            .build()

        // APIアクセス用オブジェクトを作成
        api = retrofit.create(TodoApi::class.java)
    }

    fun provideTodoApi(): TodoApi {
        return api
    }
}