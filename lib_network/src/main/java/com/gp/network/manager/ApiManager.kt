package com.gp.network.manager

import com.gp.network.api.ApiService
import com.gp.network.api.BaiduService
import com.gp.network.constant.BAIDU_URL
import com.gp.network.constant.BASE_URL

object ApiManager {
    val api by lazy { HttpManager.create(ApiService::class.java, BASE_URL) }

    val baidu by lazy { HttpManager.create(BaiduService::class.java, BAIDU_URL) }
}