package com.gp.framework.utils

import android.annotation.SuppressLint
import android.app.ActivityManager
import android.content.Context
import android.content.Context.ACCESSIBILITY_SERVICE
import android.content.ContextWrapper
import android.content.Intent
import android.content.res.Configuration
import android.content.res.Resources
import android.os.Build
import android.os.LocaleList
import java.util.Locale
import kotlin.system.exitProcess

/**
 * @Author wuleizhenshang
 * @Email wuleizhenshang@163.com
 * @Date 2024/4/8 22:07
 * @Description: TODO
 */
object LanguageChangeUtils {

    /**
     * 设置语言
     */
    fun changeAppLanguage(context: Context): Context {
        return updateResources(context, getSelectLanguageLocale())
    }

    /**
     * update
     */
    @SuppressLint("ObsoleteSdkInt")
    private fun updateResources(context: Context, locale: Locale):Context{
        var mContext = context
        Locale.setDefault(locale)//根据用户选择创建Locale对象

        val resources = mContext.resources
        val configuration = resources.configuration
        configuration.setLocale(locale)

        //适配android7.0，后面方法弃用了
        if (Build.VERSION.SDK_INT>=17){
            mContext = mContext.createConfigurationContext(configuration)
        }else{
            resources.updateConfiguration(configuration,resources.displayMetrics)
        }
        return mContext
    }

    /**
     * 从首选项获取用户选择的语言种类
     */
    fun getSelectLanguageLocale():Locale{
        if (MMKVUtil.get(MMKV_TYPE.APP).decodeString("LANGUAGE").equals("ENGLISH")){
            return Locale.ENGLISH
        }
        return Locale.CHINESE
    }

    fun restartApplication(context: Context){
        val intent =
            context.packageManager.getLaunchIntentForPackage(context.packageName)
        intent?.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
        context.startActivity(intent)
        exitProcess(0)
    }
}