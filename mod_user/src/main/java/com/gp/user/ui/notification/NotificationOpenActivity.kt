package com.gp.user.ui.notification

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.gp.common.constant.USER_ACTIVITY_NOTIFICATION
import com.gp.mod_user.R

@Route(path = USER_ACTIVITY_NOTIFICATION)
class NotificationOpenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification_open)
    }
}