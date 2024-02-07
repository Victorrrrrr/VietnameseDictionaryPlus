package com.gp.dictionary

import android.app.Application
import android.content.Context

/**
 * @Author: gxy
 * @Email: yyguanxiongyao@163.com
 * @Date: 2024/1/28:23:37.
 * @Desrciption: 描述
 */
class MainApplication : Application() {

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)

    }

    override fun onCreate() {
        super.onCreate()

        // TODO：初始化sdk等
    }

    /**
     * 注册APP前后台切换监听
     */
    private fun appFrontBackRegister() {

    }

    /**
     * 注册Activity生命周期监听
     */
    private fun registerActivityLifecycle() {

    }

}