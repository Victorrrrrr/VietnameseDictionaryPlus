package com.gp.dictionary.task

import android.app.Application
import com.alibaba.android.arouter.BuildConfig
import com.alibaba.android.arouter.launcher.ARouter
import com.gp.common.constant.YOU_DAO_APP_ID
import com.gp.common.constant.YOU_DAO_APP_SECRET
import com.gp.framework.helper.VDHelper
import com.gp.framework.manager.AppManager
import com.gp.framework.utils.LogUtil
import com.gp.starter.task.Task
import com.gp.starter.utils.DispatcherExecutor
import com.tencent.mmkv.MMKV
import com.tencent.mmkv.MMKVLogLevel
import com.youdao.sdk.app.YouDaoApplication

import java.util.concurrent.ExecutorService

/**
 * @Author: gxy
 * @Email: yyguanxiongyao@163.com
 * @Date: 2024/1/28:23:37.
 * @Desrciption: 需要被初始化的启动任务
 *                                     /> InitMmkvTask
 * 当前的有向无环图大致为： InitVDHelperTask -> InitAppManagerTask
 *                                     \> InitARouterTask
 */
class InitVDHelperTask(val application: Application) : Task() {

    override fun needWait(): Boolean {
        return true
    }

    override fun run() {
        VDHelper.init(application, BuildConfig.DEBUG)
    }
}


/**
 * 初始化AppManager
 */
class InitAppManagerTask() : Task() {
    override fun needWait(): Boolean {
        return true
    }

    override fun dependsOn(): List<Class<out Task?>?>? {
        val tasks = mutableListOf<Class<out Task?>>()
        tasks.add(InitVDHelperTask::class.java)
        return tasks
    }

    override fun run() {
        AppManager.init(VDHelper.getApplication())
    }
}


/**
 * 初始化MMKV
 */

class InitMmkvTask() : Task() {

    //异步线程执行的Task在被调用await的时候等待
    override fun needWait(): Boolean {
        return true
    }

    //依赖某些任务，在某些任务完成后才能执行
    override fun dependsOn(): MutableList<Class<out Task>> {
        val tasks = mutableListOf<Class<out Task?>>()
        tasks.add(InitVDHelperTask::class.java)
        return tasks
    }

    //指定线程池
    override fun runOn(): ExecutorService? {
        return DispatcherExecutor.iOExecutor
    }

    //执行任务初始化
    override fun run() {
        val rootDir: String = MMKV.initialize(VDHelper.getApplication())
        MMKV.setLogLevel(
            if (BuildConfig.DEBUG) {
                MMKVLogLevel.LevelDebug
            } else {
                MMKVLogLevel.LevelError
            }
        )
        LogUtil.d("mmkv root: $rootDir", tag = "MMKV")
    }

}


/**
 * 初始化ShareManager
 */
class InitARouterTask() : Task() {
    // 异步线程执行的Task在被调用await的时候等待
    override fun needWait(): Boolean {
        return true
    }

    // 依赖某些任务，在某些任务完成后才能执行
    override fun dependsOn(): List<Class<out Task>> {
        val tasks = mutableListOf<Class<out Task?>>()
        tasks.add(InitVDHelperTask::class.java)
        return tasks
    }

    // 执行任务，任务真正的执行逻辑
    override fun run() {
        // 这两行必须写在init之前，否则这些配置在init过程中将无效
        if (BuildConfig.DEBUG) {
            // 开启打印日志
            ARouter.openLog()
            // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
            ARouter.openDebug()
        }
        ARouter.init(VDHelper.getApplication())
    }

}

/**
 * 初始化MMKV
 */

class InitYouDaoTask() : Task() {

    //异步线程执行的Task在被调用await的时候等待
    override fun needWait(): Boolean {
        return true
    }

    //依赖某些任务，在某些任务完成后才能执行
    override fun dependsOn(): MutableList<Class<out Task>> {
        val tasks = mutableListOf<Class<out Task?>>()
        tasks.add(InitVDHelperTask::class.java)
        return tasks
    }

    //指定线程池
    override fun runOn(): ExecutorService? {
        return DispatcherExecutor.iOExecutor
    }

    //执行任务初始化
    override fun run() {
        YouDaoApplication.init(VDHelper.getApplication(), YOU_DAO_APP_ID, YOU_DAO_APP_SECRET);
        LogUtil.d("youdao application init run", tag = "youdao")
    }

}




/**
 * 写法： 初始化A
 */
class InitTaskA() : Task() {

    override fun run() {
        //...
    }
}

/**
 * 写法： 初始化B
 */
class InitTaskB() : Task() {

    override fun run() {
        //...
    }
}



