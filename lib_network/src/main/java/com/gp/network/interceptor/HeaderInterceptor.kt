package com.gp.network.interceptor

import com.gp.framework.utils.LogUtil
import com.gp.network.constant.KEY_AUTHORIZATION
import com.gp.network.manager.TokenManager
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

        // 是否是认证服务的url
        val isAuth = url.contains("app-auth/oauth/token") || url.contains("app-auth/auth/register")

        // 添加认证请求头
        if (!host.isNullOrEmpty() && isAuth) {
            LogUtil.d("HeaderInterceptor: post auth", tag = "okhttp")
            newBuilder.addHeader(KEY_AUTHORIZATION, "Basic ZGljdF9jbGllbnQ6ZGljdF9zZWNyZXQ=")
        }

        // 不是认证服务，添加token作为请求头
        if(!host.isNullOrEmpty() && !isAuth) {
            val token = TokenManager.getToken()
            if(!token.isNullOrEmpty()) {
                newBuilder.addHeader(KEY_AUTHORIZATION, "Bearer ${token}")
            }
        }

        // 给有需要的接口添加Cookies 或 token
        return chain.proceed(newBuilder.build())
    }
}