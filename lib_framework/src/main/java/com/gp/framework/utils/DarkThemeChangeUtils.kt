package com.gp.framework.utils

import android.app.UiModeManager
import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatDelegate

/**
 * @Author wuleizhenshang
 * @Email wuleizhenshang@163.com
 * @Date 2024/4/8 15:06
 * @Description: TODO
 */
object DarkThemeChangeUtils {
    fun autoSetDayAndNightMode(context: Context) {
        if (MMKVUtil.get(MMKV_TYPE.APP).decodeBoolean("IS_NIGHT_MODE") == true) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
//            val systemService = context.getSystemService(Context.UI_MODE_SERVICE) as? UiModeManager
//            systemService?.let {
//                if (MMKVUtil.get(MMKV_TYPE.APP).decodeBoolean("IS_NIGHT_MODE") == true) {
//                    it.setApplicationNightMode(UiModeManager.MODE_NIGHT_YES)
//                } else {
//                    it.setApplicationNightMode(UiModeManager.MODE_NIGHT_NO)
//                }
//            }
//        } else {
//            if (MMKVUtil.get(MMKV_TYPE.APP).decodeBoolean("IS_NIGHT_MODE") == true) {
//                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
//            } else {
//                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
//            }
//        }
    }

}