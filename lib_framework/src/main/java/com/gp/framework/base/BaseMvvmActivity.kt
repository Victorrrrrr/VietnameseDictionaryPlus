package com.gp.framework.base

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.Settings
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatDelegate
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gp.framework.utils.PermissionUtils
import java.lang.reflect.ParameterizedType

abstract class BaseMvvmActivity<DB : ViewDataBinding, VM : ViewModel> : BaseDataBindActivity<DB>() {
    lateinit var mViewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        initViewModel()
        super.onCreate(savedInstanceState)
    }

    private fun initViewModel() {
        val argument = (this.javaClass.genericSuperclass as ParameterizedType).actualTypeArguments
        mViewModel = ViewModelProvider(this).get(argument[1] as Class<VM>)

        //兼容性优化
//        val superclass = javaClass.superclass//超类
//        if (superclass is ParameterizedType) {//参数泛型类型
//            val arguments = superclass.actualTypeArguments//泛型参数集合
//            for (argument in arguments) {
//                //是否为class并且ViewModel是其超类
//                if (argument is Class<*> && ViewModel::class.java.isAssignableFrom(argument)) {
//                    kotlin.runCatching {
//                        //通过反射构建ViewHolder实例
//                        mViewModel = ViewModelProvider(this).get(argument as Class<VM>)
//                    }.onFailure {
//                        it.printStackTrace()
//                    }
//                }
//            }
//        }
    }

    protected fun hasPermission(permissionName: String?): Boolean {
        return PermissionUtils.hasPermission(this, permissionName)
    }


    /**
     * 当前是否在Android11.0及以上
     */
    protected fun isAndroid11(): Boolean {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.R
    }

    /**
     * 当前是否在Android10.0及以上
     */
    protected fun isAndroid10(): Boolean {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q
    }

    /**
     * 当前是否在Android7.0及以上
     */
    protected fun isAndroid7(): Boolean {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.N
    }

    /**
     * 当前是否在Android6.0及以上
     */
    protected fun isAndroid6(): Boolean {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
    }

    @RequiresApi(Build.VERSION_CODES.R)
    protected fun isStorageManager(): Boolean {
        return Environment.isExternalStorageManager()
    }


    protected fun requestPermission(permissionName: String) {
        PermissionUtils.requestPermission(this, permissionName)
    }


    /**
     * 请求外部存储管理 Android11版本时获取文件读写权限时调用
     */
    protected fun requestManageExternalStorage() {
        val intent = Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION)
        intent.setData(Uri.parse("package:$packageName"))
        startActivityForResult(intent, PermissionUtils.REQUEST_MANAGE_EXTERNAL_STORAGE_CODE)
    }

    protected fun noNight() {
        delegate.localNightMode = AppCompatDelegate.MODE_NIGHT_NO

    }

}