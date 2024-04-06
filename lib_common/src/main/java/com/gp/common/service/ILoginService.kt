package com.gp.common.service

import android.content.Context
import com.alibaba.android.arouter.facade.template.IProvider

interface ILoginService: IProvider {

    fun toLogin(context: Context)

    /**
     * 是否登陆
     */
    fun isLogin() : Boolean

}