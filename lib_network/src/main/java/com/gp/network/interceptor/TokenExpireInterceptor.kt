package com.gp.network.interceptor

import com.gp.common.manager.UserInfoManager
import com.gp.common.provider.LoginServiceProvider
import com.gp.framework.helper.VDHelper
import com.gp.network.manager.TokenManager
import okhttp3.Interceptor
import okhttp3.Response

/**
 * Token 过期检测 拦截器
 */
class TokenExpireInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val response = chain.proceed(request)
        // 401 无访问权限
        // 403 登录状态过期
        // 700 没有登录，请先登录
        if(response.code() == 401 || response.code() == 403 || response.code() == 700) {
            // 清除token
            TokenManager.clearToken()
            TokenManager.clearBaiduToken()
            // TODO 重新获取游客模式的token

            // 清除用户信息
            UserInfoManager.clearAll()
            // 跳转到登陆页面
            LoginServiceProvider.toLogin(VDHelper.getApplication())
        }
        return response

    }
}