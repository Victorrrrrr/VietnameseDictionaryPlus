package com.gp.login.service

import android.content.Context
import android.content.Intent
import com.alibaba.android.arouter.facade.annotation.Route
import com.gp.common.constant.LOGIN_SERVICE_LOGIN
import com.gp.common.manager.UserInfoManager
import com.gp.common.provider.LoginServiceProvider
import com.gp.common.service.ILoginService
import com.gp.login.login.LoginActivity


@Route(path = LOGIN_SERVICE_LOGIN)
class LoginService : ILoginService{
    override fun toLogin(context: Context) {
        context.startActivity(Intent(context, LoginActivity::class.java))
    }

    override fun isLogin(): Boolean {
        // 根据用户名是否为空判断
        return UserInfoManager.getUserName().isNotEmpty()
    }

    override fun init(context: Context?) {

    }
}