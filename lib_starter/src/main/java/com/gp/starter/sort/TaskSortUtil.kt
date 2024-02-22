package com.gp.starter.sort

import android.util.ArraySet
import com.gp.starter.task.Task
import com.gp.starter.utils.DispatcherLog

/**
 * @Author: gxy
 * @Email: yyguanxiongyao@163.com
 * @Date: 2024/2/14:16:08.
 * @Desrciption: 描述
 */
object TaskSortUtil {

    // 高优先级的Task
    private val sNewTasksHigh: MutableList<Task> = ArrayList()

    /**
     * 任务的有向无环图的拓扑排序
     *
     * @return
     */
    @Synchronized
    fun getSortResult(
        originTasks: List<Task>,
        clsLaunchTasks: List<Class<out Task>>
    ): List<Task> {
        val makeTime = System.currentTimeMillis()
        val dependSet: MutableSet<Int> = ArraySet()
        val graph = DirectionGraph(originTasks.size)

        for (i in originTasks.indices) {
            val task = originTasks[i]
            if(task.isSend || task.dependsOn().isNullOrEmpty()) {
                continue
            }

            task.dependsOn()?.let { list ->
                for (clazz in list) {
                    clazz?.let { cls ->
                        val indexOfDepend = getIndexOfTask(originTasks, clsLaunchTasks, cls)
                        check(indexOfDepend >= 0) {
                            task.javaClass.simpleName +
                                    " depends on " + cls?.simpleName + "can not be found in task list "
                        }
                        dependSet.add(indexOfDepend)
                        graph.addEdge(indexOfDepend, i)
                    }
                }
            }
        }
        val indexList: List<Int> = graph.topologicalSort()
        val newTasksAll = getResultTasks(originTasks, dependSet, indexList)
        DispatcherLog.i("task analyse cost makeTime " + (System.currentTimeMillis() - makeTime))
        printAllTaskName(newTasksAll, false)
        return newTasksAll
    }

    /**
     * 获取最终任务列表
     */
    private fun getResultTasks(
        originTasks: List<Task>,
        dependSet: Set<Int>,
        indexList: List<Int>
    ): List<Task> {
        val newTasksAll : MutableList<Task> = ArrayList(originTasks.size)
        // 被其他任务以来的
        val newTasksDepended: MutableList<Task> = ArrayList()
        // 没有依赖的
        val newTaskWithOutDepend: MutableList<Task> = ArrayList()
        // 需要提升自己优先级的，先执行（这个显示相对于没有依赖的先）
        val newTasksRunAsSoon: MutableList<Task> = ArrayList()

        for (index in indexList) {
             if (dependSet.contains(index)) {
                 newTasksDepended.add(originTasks[index])
             } else {
                 val task = originTasks[index]
                 if(task.needRunAsSoon()) {
                     newTasksRunAsSoon.add(task)
                 } else {
                     newTaskWithOutDepend.add(task)
                 }
             }
        }
        // 顺序：被别人依赖的————》需要提升自己优先级的————》需要被等待的————》没有依赖的
        sNewTasksHigh.addAll(newTasksDepended)
        sNewTasksHigh.addAll(newTasksRunAsSoon)
        newTasksAll.addAll(sNewTasksHigh)
        newTasksAll.addAll(newTaskWithOutDepend)
        return newTasksAll
    }


    private fun printAllTaskName(newTasksAll: List<Task>, isPrintName: Boolean) {
        if (!isPrintName) {
            return
        }
        for (task in newTasksAll) {
            DispatcherLog.i(task.javaClass.simpleName)
        }
    }

    val tasksHigh: List<Task>
        get() = sNewTasksHigh



    /**
     * 获取任务在任务列表中的index
     *
     * @param originTasks
     * @param clsLaunchTasks
     * @param cls
     * @return
     */
    private fun getIndexOfTask(
        originTasks: List<Task>,
        claLaunchTasks: List<Class<out Task>>,
        cls: Class<*>
    ): Int {
        val index = claLaunchTasks.indexOf(cls)
        if (index >= 0) {
            return index
        }

        // 仅仅是保护性代码
        val size = originTasks.size
        for (i in 0 until size) {
            if (cls.simpleName == originTasks[i].javaClass.simpleName) {
                return i
            }
        }
        return index
    }

}