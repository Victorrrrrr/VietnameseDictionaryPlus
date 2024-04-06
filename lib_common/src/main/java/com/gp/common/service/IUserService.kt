package com.gp.common.service

import android.content.Context
import com.alibaba.android.arouter.facade.template.IProvider

interface IUserService: IProvider {

    fun toUserInfo(context: Context)

    fun toAbout(context: Context)

    fun toCollection(context: Context)

    fun toHistory(context: Context)

    fun toLanguage(context: Context)

    fun toNotification(context: Context)

    fun toWordBook(context: Context)


}