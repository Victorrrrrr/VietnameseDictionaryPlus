package com.gp.network.manager

import com.gp.common.constant.HTTP_BAIDU_TOKEN_INFO
import com.gp.common.constant.HTTP_TOKEN_INFO
import com.gp.framework.utils.LogUtil
import com.gp.framework.utils.XLogger
import com.tencent.mmkv.MMKV


/**
 * Token 管理类
 */
object TokenManager {

    /**
     * 保存Token
     */
    fun saveToken(token: String) {
        val mmkv = MMKV.defaultMMKV()
        LogUtil.d(token, tag = "token")
        mmkv.encode(HTTP_TOKEN_INFO, token)
    }


    /**
     * 获取Token
     * @return token
     */
    fun getToken() : String? {
        val mmkv = MMKV.defaultMMKV()
        return mmkv.decodeString(HTTP_TOKEN_INFO, "")
    }


    /**
     * 清除token
     */
    fun clearToken() {
        saveToken("")
    }


    /**
     * 保存Token
     */
    fun saveBaiduToken(token: String) {
        val mmkv = MMKV.defaultMMKV()
        LogUtil.d(token, tag = "token")
        mmkv.encode(HTTP_BAIDU_TOKEN_INFO, token)
    }


    /**
     * 获取Token
     * @return token
     */
    fun getBaiduToken() : String? {
        val mmkv = MMKV.defaultMMKV()
        return mmkv.decodeString(HTTP_BAIDU_TOKEN_INFO, "")
    }


    /**
     * 清除token
     */
    fun clearBaiduToken() {
        saveBaiduToken("")
    }

}