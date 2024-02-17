package com.gp.lib_starter.utils

import com.gp.lib_framework.helper.VDHelper
import com.gp.lib_framework.utils.LogUtil

/**
 * @Author: gxy
 * @Email: yyguanxiongyao@163.com
 * @Date: 2024/2/13:14:43.
 * @Desrciption: 分配器日志
 */
object DispatcherLog {
    var isDebug = VDHelper.isDebug()

    @JvmStatic
    fun i(msg: String?) {
        if(msg == null) {
            return
        }
        LogUtil.i(msg, tag = "StartTask")
    }
}