package com.gp.network.interceptor

import com.gp.framework.utils.LogUtil
import com.gp.network.constant.KEY_AUTHORIZATION
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

        if (!host.isNullOrEmpty() && url.contains("app-auth/oauth/token")) {
            LogUtil.d("HeaderInterceptor: post auth", tag = "okhttp")
            newBuilder.addHeader(KEY_AUTHORIZATION, "Basic ZGljdF9jbGllbnQ6ZGljdF9zZWNyZXQ=")
        }


//        val token = TokenManager.getToken()
//        LogUtil.e("HeaderInterceptor:token:$token", tag = "okhttp")
//        if(!token.isNullOrEmpty()) {
//            newBuilder.addHeader(KEY_TOKEN, token)
//        }

        // 给有需要的接口添加Cookies 或 token
        return chain.proceed(newBuilder.build())
    }
}