package com.gp.network.interceptor

import okhttp3.Interceptor
import okhttp3.Response


/**
 * 头部信息拦截器
 */
class HeaderInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val newBuilder = request.newBuilder()
        newBuilder.addHeader("Content-Type", "application/json; charset=utf-8")

        val host = request.url().host()
        val url = request.url().toString()

        // 给有需要的接口添加Cookies 或 token
        return chain.proceed(newBuilder.build())
    }
}