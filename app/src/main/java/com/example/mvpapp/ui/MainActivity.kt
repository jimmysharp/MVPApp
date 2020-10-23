package com.example.mvpapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView
import com.example.mvpapp.R

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // レイアウトの読み込み
        setContentView(R.layout.main_activity)

        // AppBarのセットアップ
        setSupportActionBar(findViewById(R.id.toolbar))

        // Navigation Architecture Component関連の設定
        // AppBarとNavigationを連携させる
        // 矢印ボタンやタイトルがNavigationの設定を反映するようになる
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        appBarConfiguration =
            AppBarConfiguration.Builder(navController.graph)
                .build()
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    override fun onSupportNavigateUp(): Boolean {
        // 「戻る」操作を上書きする
        // Navigationで戻る先があれば戻る
        // なければデフォルト動作(アプリ終了)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        return navController.navigateUp(appBarConfiguration) ||
                super.onSupportNavigateUp()
    }
}