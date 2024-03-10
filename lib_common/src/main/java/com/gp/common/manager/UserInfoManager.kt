package com.gp.common.manager

import com.gp.common.constant.USER_ACCOUNT
import com.gp.common.constant.USER_AVATAR
import com.gp.common.constant.USER_EMAIL
import com.gp.common.constant.USER_GENDER
import com.gp.common.constant.USER_USERNAME
import com.gp.common.model.Ext
import com.gp.common.model.UserInfo
import com.tencent.mmkv.MMKV


/**
 * 用户信息管理类
 */
object UserInfoManager {

    fun saveUserInfo(userInfo: UserInfo) {
        val mmkv = MMKV.defaultMMKV()
        mmkv.encode(USER_ACCOUNT, userInfo.account)
        mmkv.encode(USER_EMAIL, userInfo.email)
        mmkv.encode(USER_USERNAME, userInfo.username)
        mmkv.encode(USER_AVATAR, userInfo.ext?.avatar ?: "")
        mmkv.encode(USER_GENDER, userInfo.ext?.gender ?: "")
    }


    fun getUserName() : String {
        val mmkv = MMKV.defaultMMKV()
        return mmkv.decodeString(USER_USERNAME, "") ?: ""
    }

    fun clearAll() {
        val userInfo = UserInfo()
        saveUserInfo(userInfo)
    }

}