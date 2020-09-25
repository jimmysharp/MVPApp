package com.example.mvpapp

import android.app.Application
import timber.log.Timber

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        // デバッグビルドのみログ記録を有効化
        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
    }
}
