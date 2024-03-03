package com.gp.main.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.gp.common.provider.MainServiceProvider
import com.gp.main.R

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        MainServiceProvider.toMain(this)
        finish()

    }
}