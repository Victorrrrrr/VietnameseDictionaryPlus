package com.gp.user.ui.notification

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.gp.user.ui.notification.NotificationShowSearchWordUtils.createNotificationShowDown
import com.gp.user.ui.notification.NotificationShowSearchWordUtils.createNotificationShowUp
import com.gp.user.ui.notification.NotificationShowSearchWordUtils.showNotification

/**
 * @Author wuleizhenshang
 * @Email wuleizhenshang@163.com
 * @Date 2024/4/11 21:03
 * @Description: TODO
 */
class NotificationSearchWordBroadCast : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val action = intent.action ?: return
        Log.i("TAGG", "接收到通知$action")
        if (action == "notification_show_search_word_option_up") {
            val notificationShowUp = createNotificationShowUp(context)
            showNotification(context, notificationShowUp)
        } else if (action == "notification_show_search_word_option_down") {
            val notificationShowDown = createNotificationShowDown(context)
            showNotification(context, notificationShowDown)
        } else if (action == "notification_show_search_word_jump_activity") {
            //TODO 跳转到单词搜索
        } else if (action == "notification_show_search_word_sound") {
            //TODO  更新通知
        } else if (action == "notification_show_search_word_star") {
            //TODO 更新通知
        } else if (action == "notification_show_search_word_next") {
            //TODO 更新通知
        }
    }
}