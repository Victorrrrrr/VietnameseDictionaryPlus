package com.gp.user.ui.notification

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Context.NOTIFICATION_SERVICE
import android.content.Intent
import android.os.Build
import android.view.View
import android.widget.RemoteViews
import androidx.core.app.NotificationCompat
import com.gp.mod_user.R

/**
 * @Author wuleizhenshang
 * @Email wuleizhenshang@163.com
 * @Date 2024/4/12 12:20
 * @Description: TODO
 */
object NotificationShowSearchWordUtils {

    fun addNotificationChannel(context: Context){
        val manager = context.getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        //在发送通知前创建自定义通知渠道
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val channel =
                NotificationChannel("1", "channel1", NotificationManager.IMPORTANCE_DEFAULT)
            channel.enableLights(true)
            channel.setShowBadge(true)
            manager.createNotificationChannel(channel)
        }
    }

    @SuppressLint("RemoteViewLayout")
    fun createNotificationShowDown(context: Context):NotificationCompat.Builder{
        val builder = NotificationCompat.Builder(context, "1")
            .setWhen(System.currentTimeMillis())
            .setSmallIcon(com.gp.lib_widget.R.mipmap.ic_launcher_foreground)
            .setAutoCancel(false)
            .setPriority(NotificationCompat.PRIORITY_MAX)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){

            val remoteViews = RemoteViews(context.packageName, R.layout.notification_show_search_word)
            remoteViews.setViewVisibility(R.id.view_up, View.INVISIBLE)
            remoteViews.setViewVisibility(R.id.img_fold_up,View.INVISIBLE)
            remoteViews.setViewVisibility(R.id.view_down, View.VISIBLE)
            remoteViews.setViewVisibility(R.id.img_fold_down,View.VISIBLE)

            //设置点击
            setPendingIntent(remoteViews,context)

            builder.setContent(remoteViews)
        }
        return builder
    }

    @SuppressLint("RemoteViewLayout")
    fun createNotificationShowUp(context: Context):NotificationCompat.Builder{
        val builder = NotificationCompat.Builder(context, "1")
            .setWhen(System.currentTimeMillis())
            .setSmallIcon(com.gp.lib_widget.R.mipmap.ic_launcher_foreground)
            .setAutoCancel(false)
            .setPriority(NotificationCompat.PRIORITY_MAX)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){

            val remoteViews = RemoteViews(context.packageName, R.layout.notification_show_search_word)
            remoteViews.setViewVisibility(R.id.view_up, View.VISIBLE)
            remoteViews.setViewVisibility(R.id.img_fold_up,View.VISIBLE)
            remoteViews.setViewVisibility(R.id.view_down, View.INVISIBLE)
            remoteViews.setViewVisibility(R.id.img_fold_down,View.INVISIBLE)

            //设置点击
            setPendingIntent(remoteViews,context)

            builder.setContent(remoteViews)
        }
        return builder
    }

    fun setPendingIntent(remoteViews:RemoteViews,context: Context){
        val intent1 = Intent(context,NotificationSearchWordBroadCast::class.java)
        intent1.action = "notification_show_search_word_option_up"
        val pendingIntent1 =
            PendingIntent.getBroadcast(context, 0, intent1, PendingIntent.FLAG_IMMUTABLE)

        val intent2 = Intent(context,NotificationSearchWordBroadCast::class.java)
        intent2.action = "notification_show_search_word_option_down"
        val pendingIntent2 =
            PendingIntent.getBroadcast(context, 1, intent2, PendingIntent.FLAG_IMMUTABLE)

        val intent3 = Intent(context,NotificationSearchWordBroadCast::class.java)
        intent3.action = "notification_show_search_word_jump_activity"
        val pendingIntent3 =
            PendingIntent.getBroadcast(context, 2, intent3, PendingIntent.FLAG_IMMUTABLE)

        val intent4 = Intent(context,NotificationSearchWordBroadCast::class.java)
        intent4.action = "notification_show_search_word_sound"
        val pendingIntent4 =
            PendingIntent.getBroadcast(context, 3, intent4, PendingIntent.FLAG_IMMUTABLE)

        val intent5 = Intent(context,NotificationSearchWordBroadCast::class.java)
        intent5.action = "notification_show_search_word_star"
        val pendingIntent5 =
            PendingIntent.getBroadcast(context, 4, intent5, PendingIntent.FLAG_IMMUTABLE)

        val intent6 = Intent(context,NotificationSearchWordBroadCast::class.java)
        intent6.action = "notification_show_search_word_next"
        val pendingIntent6 =
            PendingIntent.getBroadcast(context, 5, intent6, PendingIntent.FLAG_IMMUTABLE)

        remoteViews.setOnClickPendingIntent(R.id.img_fold_down,pendingIntent1)
        remoteViews.setOnClickPendingIntent(R.id.img_fold_up,pendingIntent2)
        remoteViews.setOnClickPendingIntent(R.id.view_up,pendingIntent3)
        remoteViews.setOnClickPendingIntent(R.id.img_sound,pendingIntent4)
        remoteViews.setOnClickPendingIntent(R.id.img_star,pendingIntent5)
        remoteViews.setOnClickPendingIntent(R.id.img_next,pendingIntent6)
    }

    fun showNotification(context: Context,builder:NotificationCompat.Builder){
        addNotificationChannel(context)
        val manager = context.getSystemService(NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // 发送通知
            manager.notify(1, builder.build())
        }else{

        }
    }

    fun closeNotification(context: Context){
        val manager = context.getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        manager.cancel(1)
    }
}