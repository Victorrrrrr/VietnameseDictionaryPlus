package com.gp.dictionary

import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Bundle
import com.gp.dictionary.task.InitAppManagerTask
import com.gp.dictionary.task.InitTaskA
import com.gp.dictionary.task.InitVDHelperTask
import com.gp.lib_framework.manager.AppFrontBack
import com.gp.lib_framework.manager.AppFrontBackListener
import com.gp.lib_framework.toast.TipsToast
import com.gp.lib_framework.utils.LogUtil
import com.gp.lib_starter.dispatcher.TaskDispatcher

/**
 * @Author: gxy
 * @Email: yyguanxiongyao@163.com
 * @Date: 2024/1/28:23:37.
 * @Desrciption: 应用类
 */
class MainApplication : Application() {

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)

    }

    override fun onCreate() {
        super.onCreate()

        // 注册APP前后台切换监听
        appFrontBackRegister()
        // App启动立即注册监听
        registerActivityLifecycle()
        TipsToast.init(this)

        // 1. 启动器，TaskDispatcher初始化
        TaskDispatcher.init(this)
        // 2. 创建dispatcher实例
        val dispatcher: TaskDispatcher = TaskDispatcher.createInstance()

        // 3. 添加任务并且启动任务
        dispatcher.addTask(InitVDHelperTask(this))
            .addTask(InitAppManagerTask())
            .addTask(InitTaskA())
            .start()
    }

    /**
     * 注册APP前后台切换监听
     */
    private fun appFrontBackRegister() {
        AppFrontBack.register(this, object : AppFrontBackListener {
            override fun onFront(activity: Activity?) {
                LogUtil.d("onBack")
            }

            override fun onBack(activity: Activity?) {
                LogUtil.d("onFront")
            }
        })
    }

    /**
     * 注册Activity生命周期监听
     */
    private fun registerActivityLifecycle() {
        registerActivityLifecycleCallbacks(object : ActivityLifecycleCallbacks{
            override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {

            }

            override fun onActivityStarted(activity: Activity) {

            }

            override fun onActivityResumed(activity: Activity) {

            }

            override fun onActivityPaused(activity: Activity) {

            }

            override fun onActivityStopped(activity: Activity) {

            }

            override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {

            }

            override fun onActivityDestroyed(activity: Activity) {

            }

        })
    }

}