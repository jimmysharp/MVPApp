package com.example.mvpapp.data.source

import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

object TodoApiInjection {

    // アクセス先URL
    // エミュレータ実行PCのlocalhost:3000にサーバが立っているものとする
    const val API_BASEURL = "https://10.0.2.2:3000/"

    private val retrofit: Retrofit
    private val service: TodoApiService

    init {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val httpClient = OkHttpClient.Builder()
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