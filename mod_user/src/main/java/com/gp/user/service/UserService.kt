package com.gp.user.service

import android.content.Context
import android.content.Intent
import com.alibaba.android.arouter.facade.annotation.Route
import com.gp.common.constant.USER_SERVICE_USER
import com.gp.common.service.IUserService
import com.gp.user.ui.about.AboutActivity
import com.gp.user.ui.collection.CollectionActivity
import com.gp.user.ui.history.HistoryActivity
import com.gp.user.ui.info.UserInfoActivity
import com.gp.user.ui.language.LanguageActivity
import com.gp.user.ui.notification.NotificationOpenActivity
import com.gp.user.ui.wordbook.WordBookConfigActivity

@Route(path = USER_SERVICE_USER)
class UserService: IUserService {
    override fun toUserInfo(context: Context) {
        context.startActivity(Intent(context, UserInfoActivity::class.java))
    }

    override fun toAbout(context: Context) {
        context.startActivity(Intent(context, AboutActivity::class.java))
    }

    override fun toCollection(context: Context) {
        context.startActivity(Intent(context, CollectionActivity::class.java))
    }

    override fun toHistory(context: Context) {
        context.startActivity(Intent(context, HistoryActivity::class.java))
    }

    override fun toLanguage(context: Context) {
        context.startActivity(Intent(context, LanguageActivity::class.java))
    }

    override fun toNotification(context: Context) {
        context.startActivity(Intent(context, NotificationOpenActivity::class.java))
    }

    override fun toWordBook(context: Context) {
        context.startActivity(Intent(context, WordBookConfigActivity::class.java))
    }

    override fun init(context: Context?) {

    }

}