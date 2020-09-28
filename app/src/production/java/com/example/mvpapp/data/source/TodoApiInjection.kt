package com.example.mvpapp.data.source

import com.squareup.moshi.Moshi
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

object TodoApiInjection {

    // アクセス先URL
    // エミュレータ実行PCのlocalhost:3000にサーバが立っているものとする
    private const val API_BASEURL = "https://10.0.2.2:3000/"

    // BASIC認証
    // 今回はサンプルなのでハードコーディング
    private const val AUTH_USER = "hoge"
    private const val AUTH_PASSWORD = "Hoge"

    private val retrofit: Retrofit
    private val service: TodoApiService

    init {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val httpClient = OkHttpClient.Builder()
            .authenticator { _, response ->
                val credential = Credentials.basic(AUTH_USER, AUTH_PASSWORD)
                response.request.newBuilder()
                    .header("Authorization", credential)
                    .build()
            }
            .addInterceptor(loggingInterceptor)
            .readTimeout(30, TimeUnit.SECONDS)
            .build()

        val moshi = Moshi.Builder()
            .build()

        retrofit = Retrofit.Builder()
            .baseUrl(API_BASEURL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(httpClient)
            .build()

        service = retrofit.create(TodoApiService::class.java)
    }

    fun provideTodoApiService(): TodoApiService {
        return service
    }
}