package com.gp.user.ui.notification

import android.annotation.SuppressLint
import android.app.NotificationManager
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import com.alibaba.android.arouter.facade.annotation.Route
import com.gp.common.constant.USER_ACTIVITY_NOTIFICATION
import com.gp.common.provider.MainServiceProvider
import com.gp.framework.base.BaseDataBindActivity
import com.gp.framework.ext.onClick
import com.gp.framework.utils.MMKVUtil
import com.gp.framework.utils.MMKV_TYPE
import com.gp.mod_user.databinding.ActivityNotificationOpenBinding


@Route(path = USER_ACTIVITY_NOTIFICATION)
class NotificationOpenActivity : BaseDataBindActivity<ActivityNotificationOpenBinding>() {

    @SuppressLint("RemoteViewLayout")
    override fun initView(savedInstanceState: Bundle?) {

        //back
        mBinding.toolbarLanguage.setNavigationOnClickListener {
            onBackPressed()
        }

        //init switch
        mBinding.switchNotificationSearch.isChecked =
            MMKVUtil.get(MMKV_TYPE.APP).decodeBoolean("IS_OPEN_NOTIFICATION_SEARCH") == true
        if (mBinding.switchNotificationSearch.isChecked){
            openNotification()
        }

        //click
        mBinding.viewSwitch.onClick {
            if (mBinding.switchNotificationSearch.isChecked) {
                mBinding.switchNotificationSearch.isChecked = false
                MMKVUtil.get(MMKV_TYPE.APP).encode("IS_OPEN_NOTIFICATION_SEARCH", false)
                closeNotification()
            } else {
                mBinding.switchNotificationSearch.isChecked = true
                MMKVUtil.get(MMKV_TYPE.APP).encode("IS_OPEN_NOTIFICATION_SEARCH", true)
                openNotification()
            }
        }
    }

    fun openNotification() {
        val manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        // 检测该应用是否有通知权限
        when (manager.areNotificationsEnabled()) {
            true -> {
                showNotification()
            }
            false -> {
                mBinding.switchNotificationSearch.isChecked = false
                MMKVUtil.get(MMKV_TYPE.APP).encode("IS_OPEN_NOTIFICATION_SEARCH", false)
                val intent = Intent(Settings.ACTION_APP_NOTIFICATION_SETTINGS)
                intent.putExtra(Settings.EXTRA_APP_PACKAGE,packageName)
                startActivity(intent)
            }
        }
    }

    @SuppressLint("RemoteViewLayout")
    fun showNotification(){
        NotificationShowSearchWordUtils.showNotification(this
            , NotificationShowSearchWordUtils.createNotificationShowUp(this))
    }

    fun closeNotification(){
        NotificationShowSearchWordUtils.closeNotification(this)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        MainServiceProvider.toMain(this, 3)
    }
}