package com.gp.lib_starter.task

import android.content.Context
import com.gp.lib_starter.dispatcher.TaskDispatcher
import java.util.concurrent.CountDownLatch
import android.os.Process
import com.gp.lib_framework.helper.VDHelper
import com.gp.lib_starter.utils.DispatcherExecutor
import java.util.concurrent.Executor
import java.util.concurrent.ExecutorService

/**
 * @Author: gxy
 * @Email: yyguanxiongyao@163.com
 * @Date: 2024/2/13:14:06.
 * @Desrciption: 任务抽象类
 */
abstract class Task : ITask{
    protected var mContext: Context? = null

    // 当前进程是否主进程
    protected var mIsMainProcess: Boolean = TaskDispatcher.isMainProcess

    // 是否正在等待
    @Volatile
    var isWaiting = false

    // 是否正在执行
    @Volatile
    var isRunning = false

    // Task是否执行完成
    @Volatile
    var isFinished = false

    // Task是否已经被分发
    @Volatile
    var isSend = false

    // 当前Task依赖的Task数量（需要等待被依赖的Task执行完毕才能执行自己），默认没有依赖
    private val mDepends = CountDownLatch(
        dependsOn()?.size ?: 0
    )

    /**
     * 当前Task等待，让依赖的Task先执行
     */
    fun waitToSatisfy() {
        try {
            mDepends.await()
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
    }

    /**
     * 依赖的Task执行完一个
     */
    fun satisfy() {
        mDepends.countDown()
    }

    /**
     * 是否需要尽快执行，解决特殊场景的问题：一个Task耗时非常多但是优先级却一般，很有可能开始的时间较晚
     * 导致最后只能在等它，这种可以早开始
     *
     * @return
     */
    fun needRunAsSoon(): Boolean {
        return false
    }


    /**
     *  Task的优先级，运行在主线程则不要去改优先级
     */
    override fun priority(): Int {
        return Process.THREAD_PRIORITY_BACKGROUND
    }

    /**
     * Task执行在哪个线程池，默认都在IO线程池
     * CPU密集型的一定要切换到DispatcherExecutor.getCPUExecutor()
     *
     */
    override fun runOn(): ExecutorService? {
        return DispatcherExecutor.iOExecutor
    }

    /**
     * 异步线程执行的Task是否需要在被调用await的时候等待，默认不需要
     *
     * @return
     */
    override fun needWait(): Boolean {
        return false
    }

    /**
     * 当前Task依赖的Task集合（需要等待被依赖的Task执行完毕才能执行自己），默认没有依赖
     *
     * @return
     */
    override fun dependsOn(): List<Class<out Task?>?>? {
        return null
    }

    /**
     * 运行在主线程
     */
    override fun runOnMainThread(): Boolean {
        return false
    }


    override val tailRunnable: Runnable?
        get() = null

    override fun setTaskCallBack(callBack: TaskCallBack?) {}

    override fun needCall(): Boolean {
        return false
    }

    /**
     * 是否只在主进程，默认是
     *
     * @return
     */
    override fun onlyInMainProcess(): Boolean {
        return true
    }


}

/**
 * 主线程任务
 */
abstract class MainTask : Task() {
    override fun runOnMainThread(): Boolean {
        return true
    }
}