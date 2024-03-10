package com.gp.common.provider

import android.content.Context
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.launcher.ARouter
import com.gp.common.constant.LOGIN_SERVICE_LOGIN
import com.gp.common.service.ILoginService
import kotlin.math.log

object LoginServiceProvider {

    @Autowired(name = LOGIN_SERVICE_LOGIN)
    lateinit var loginService: ILoginService

    init {
        ARouter.getInstance().inject(this)
    }


    fun toLogin(context: Context) {
        loginService.toLogin(context)
    }

    fun isLogin() : Boolean {
        return loginService.isLogin()
    }

}