package com.gp.dictionary

import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.res.Configuration
import android.content.res.Resources
import android.os.Build
import android.os.Bundle
import androidx.multidex.MultiDex
import com.gp.dictionary.task.InitARouterTask
import com.gp.dictionary.task.InitAppManagerTask
import com.gp.dictionary.task.InitMmkvTask
import com.gp.dictionary.task.InitVDHelperTask
import com.gp.dictionary.task.InitYouDaoTask
import com.gp.framework.manager.ActivityManager
import com.gp.framework.manager.AppFrontBack
import com.gp.framework.manager.AppFrontBackListener
import com.gp.framework.toast.TipsToast
import com.gp.framework.utils.DarkThemeChangeUtils
import com.gp.framework.utils.LanguageChangeUtils
import com.gp.framework.utils.LogUtil
import com.gp.framework.utils.MMKVUtil
import com.gp.framework.utils.MMKV_TYPE
import com.gp.lib_framework.utils.StatusBarSettingHelper
import com.gp.starter.dispatcher.TaskDispatcher
import com.tencent.mmkv.MMKV

/**
 * @Author: gxy
 * @Email: yyguanxiongyao@163.com
 * @Date: 2024/1/28:23:37.
 * @Desrciption: 应用类
 */
class MainApplication : Application() {

    override fun attachBaseContext(base: Context?) {
        base?.let {
            MMKV.initialize(base)
            super.attachBaseContext(LanguageChangeUtils.changeAppLanguage(base))
            MultiDex.install(base)
        }?:super.attachBaseContext(base)
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
            .addTask(InitMmkvTask())
            .addTask(InitARouterTask())
            .addTask(InitYouDaoTask())
            .start()

        // 4. 等待，需要等待的方法执行完才可以继续往下执行
        dispatcher.await()

        DarkThemeChangeUtils.autoSetDayAndNightMode(this);//设置暗夜模式
        //对于7.0以下，需要在Application创建的时候进行语言切换,设置语言首选项
        LanguageChangeUtils.changeAppLanguage(this)
    }

    /**
     * 注册APP前后台切换监听
     */
    private fun appFrontBackRegister() {
        AppFrontBack.register(this, object : AppFrontBackListener {
            override fun onFront(activity: Activity?) {
                LogUtil.d("前台回调，onFront")
            }

            override fun onBack(activity: Activity?) {
                LogUtil.d("后台回调，onBack")
            }
        })
    }

    /**
     * 注册Activity生命周期监听
     */
    private fun registerActivityLifecycle() {
        registerActivityLifecycleCallbacks(object : ActivityLifecycleCallbacks{
            override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
                ActivityManager.push(activity)
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
                ActivityManager.pop(activity)
            }

        })
    }

    /**
     * 系统切换语言时，会回调次方法
     */
    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        LanguageChangeUtils.changeAppLanguage(this)
    }
}