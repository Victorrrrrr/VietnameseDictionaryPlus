package com.gp.network.manager

import com.gp.common.constant.HTTP_TOKEN_INFO
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


}