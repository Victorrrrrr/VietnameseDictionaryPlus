package com.gp.starter.utils

import android.app.ActivityManager
import android.content.Context
import android.os.Process
import android.text.TextUtils
import java.io.BufferedReader
import java.io.FileInputStream
import java.io.InputStreamReader

/**
 * @Author: gxy
 * @Email: yyguanxiongyao@163.com
 * @Date: 2024/2/13:19:04.
 * @Desrciption: 描述
 */
object StaterUtils {
    private var sCurProcessName: String? = null
    fun isMainProcess(context: Context) : Boolean {
        val processName = getCurProcessName(context)
        return if(processName != null && processName.contains(":")) {
            false
        } else processName != null && processName == context.packageName
    }



    private val curProcessNameFromProc: String?
        private get() {
            var cmdlineReader: BufferedReader? = null
            try {
                cmdlineReader = BufferedReader(
                    InputStreamReader(
                        FileInputStream(
                            "/proc/${Process.myPid()}/cmdline"
                        ),
                        "iso-8859-1"
                    )
                )
                var c: Int
                val processName = StringBuilder()
                while (cmdlineReader.read().also { c = it } > 0) {
                    processName.append(c.toChar())
                }
                return processName.toString()
            } catch (e: Throwable) {
                // ignore
            } finally {
                if (cmdlineReader != null) {
                    try {
                        cmdlineReader.close()
                    } catch (e: Exception) {
                        // ignore
                    }
                }
            }

            return null
        }


    fun getCurProcessName(context: Context): String? {
        val processName = sCurProcessName
        if(!TextUtils.isEmpty(processName)) {
            return processName
        }
        try {
            val pid = Process.myPid()
            val mActivityManager = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
            for (appProcess in mActivityManager.runningAppProcesses) {
                if(appProcess.pid == pid) {
                    sCurProcessName = appProcess.processName
                    return sCurProcessName
                }
            }
        } catch (e : Exception) {
            e.printStackTrace()
        }
        sCurProcessName = curProcessNameFromProc
        return sCurProcessName
    }



}