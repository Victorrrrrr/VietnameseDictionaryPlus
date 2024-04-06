package com.gp.framework.manager

import android.app.Activity
import android.app.Application
import android.os.Bundle

/**
 * @Author: gxy
 * @Email: yyguanxiongyao@163.com
 * @Date: 2024/2/15:12:02.
 * @Desrciption: App前后台切换监听
 */

object AppFrontBack {
    /**
     * 打开的Activity数量统计
     */
    private var activityStartCount = 0


    fun register(application: Application, listener: AppFrontBackListener) {
        application.registerActivityLifecycleCallbacks(object : Application.ActivityLifecycleCallbacks {
            override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {

            }

            override fun onActivityStarted(activity: Activity) {
                activityStartCount++
                if (activityStartCount == 1) {
                    listener.onFront(activity)
                }
            }

            override fun onActivityResumed(activity: Activity) {

            }

            override fun onActivityPaused(activity: Activity) {

            }

            override fun onActivityStopped(activity: Activity) {
                activityStartCount--
                if (activityStartCount == 0) {
                    listener.onBack(activity)
                }
            }

            override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {

            }

            override fun onActivityDestroyed(activity: Activity) {

            }
        })
    }

}


/**
 * App状态监听
 */
interface AppFrontBackListener {
    /**
     * 前台
     */
    fun onFront(activity: Activity?)

    /**
     * 后台
     */
    fun onBack(activity: Activity?)
}
 