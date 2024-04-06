package com.gp.framework.utils

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.ActivityCompat

object PermissionUtils {
    private var mInstance: PermissionUtils? = null
    const val READ_EXTERNAL_STORAGE = Manifest.permission.READ_EXTERNAL_STORAGE
    const val WRITE_EXTERNAL_STORAGE = Manifest.permission.WRITE_EXTERNAL_STORAGE
    const val CAMERA = Manifest.permission.CAMERA
    const val RECORD_AUDIO = Manifest.permission.RECORD_AUDIO
    const val REQUEST_STORAGE_CODE = 1001
    const val REQUEST_CAMERA_CODE = 1002
    const val REQUEST_RECORD_AUDIO = 1004
    const val REQUEST_MANAGE_EXTERNAL_STORAGE_CODE = 1000

    /**
     * 检查是有拥有某权限
     *
     * @param permission 权限名称
     * @return true 有  false 没有
     */
    @JvmStatic
    fun hasPermission(activity: Activity, permission: String?): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            activity.checkSelfPermission(permission!!) == PackageManager.PERMISSION_GRANTED
        } else {
            true
        }
    }

    /**
     * 通过权限名称获取请求码
     *
     * @param permissionName 权限名称
     * @return requestCode 权限请求码
     */
    @JvmStatic
    private fun getPermissionRequestCode(permissionName: String): Int {
        val requestCode: Int
        requestCode = when (permissionName) {
            READ_EXTERNAL_STORAGE, WRITE_EXTERNAL_STORAGE -> REQUEST_STORAGE_CODE
            CAMERA -> REQUEST_CAMERA_CODE
            RECORD_AUDIO -> REQUEST_RECORD_AUDIO
            else -> 1000
        }
        return requestCode
    }

    /**
     * 请求权限
     *
     * @param permission 权限名称
     */
    @JvmStatic
    fun requestPermission(activity: Activity?, permission: String) {
        val requestCode = getPermissionRequestCode(permission)
        //请求此权限
        ActivityCompat.requestPermissions(activity!!, arrayOf(permission), requestCode)
    }
}