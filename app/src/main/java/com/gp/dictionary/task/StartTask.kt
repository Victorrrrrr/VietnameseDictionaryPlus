package com.gp.dictionary.task

import android.app.Application
import com.gp.lib_framework.helper.VDHelper
import com.gp.lib_framework.manager.AppManager
import com.gp.lib_starter.task.Task

/**
 * @Author: gxy
 * @Email: yyguanxiongyao@163.com
 * @Date: 2024/1/28:23:37.
 * @Desrciption: 需要被初始化的启动任务
 */


class InitVDHelperTask(val application: Application) : Task() {

    override fun needWait(): Boolean {
        return true
    }

    override fun run() {
        VDHelper.init(application, true)
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
 * 初始化A
 */
class InitTaskA() : Task() {

    override fun run() {
        //...
    }
}

/**
 * 初始化B
 */
class InitTaskB() : Task() {

    override fun run() {
        //...
    }
}



