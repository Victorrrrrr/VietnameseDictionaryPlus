package com.gp.common.provider

import android.content.Context
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.launcher.ARouter
import com.gp.common.constant.USER_SERVICE_USER
import com.gp.common.service.IUserService

object UserServiceProvider {

    @Autowired(name = USER_SERVICE_USER)
    lateinit var userService: IUserService

    init {
        ARouter.getInstance().inject(this)
    }


    fun toUserInfo(context: Context) {
        userService.toUserInfo(context)
    }

    fun toAbout(context: Context) {
        userService.toAbout(context)
    }


    fun toCollection(context: Context) {
        userService.toCollection(context)
    }


    fun toHistory(context: Context) {
        userService.toHistory(context)
    }


    fun toLanguage(context: Context) {
        userService.toLanguage(context)
    }


    fun toNotification(context: Context) {
        userService.toNotification(context)
    }


    fun toWordBook(context: Context) {
        userService.toWordBook(context)
    }
    

}