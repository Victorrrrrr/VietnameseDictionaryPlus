package com.gp.network.manager

import com.gp.network.api.ApiService

object ApiManager {
    val api by lazy { HttpManager.create(ApiService::class.java) }
}