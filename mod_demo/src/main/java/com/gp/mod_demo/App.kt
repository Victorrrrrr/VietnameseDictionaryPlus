package com.gp.mod_demo

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex

class App: Application() {

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(applicationContext)
    }

    override fun onCreate() {
        super.onCreate()

    }
}