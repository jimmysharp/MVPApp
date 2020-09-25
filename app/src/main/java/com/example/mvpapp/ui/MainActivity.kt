package com.example.mvpapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
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

        // ドロワーのセットアップ
        val drawerLayout = findViewById<DrawerLayout>(R.id.drawer_layout)

        // Navigation Architecture Component関連の設定
        val navController = findNavController(R.id.nav_host_fragment)
        // AppBarとNavigationを連携させる
        // 矢印ボタンやタイトルがNavigationの設定を反映するようになる
        appBarConfiguration =
            AppBarConfiguration.Builder(navController.graph)
                .setOpenableLayout(drawerLayout)
                .build()
        setupActionBarWithNavController(navController, appBarConfiguration)
        // ドロワーとNavigationを連携させる
        findViewById<NavigationView>(R.id.nav_view)
            .setupWithNavController(navController)
    }

    // 「戻る」操作を上書きする
    // Navigationで戻る先があれば戻る
    // なければデフォルト動作(アプリ終了)
    override fun onSupportNavigateUp(): Boolean {
        return findNavController(R.id.nav_host_fragment).navigateUp(appBarConfiguration) ||
                super.onSupportNavigateUp()
    }
}