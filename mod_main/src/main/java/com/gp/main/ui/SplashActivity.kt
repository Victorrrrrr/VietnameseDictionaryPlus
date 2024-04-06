package com.gp.main.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.gp.common.constant.*
import com.gp.common.provider.MainServiceProvider
import com.gp.framework.base.BaseMvvmActivity
import com.gp.framework.ext.countDownCoroutines
import com.gp.framework.ext.onClick
import com.gp.lib_framework.utils.StatusBarSettingHelper
import com.gp.lib_widget.R

import com.gp.main.databinding.ActivitySplashBinding
import com.gp.main.ui.main.MainViewModel
import com.gp.network.manager.TokenManager

class SplashActivity : BaseMvvmActivity<ActivitySplashBinding, MainViewModel>() {

    override fun preCreate() {
        // 避免每次启动都走splash界面，针对某米手机
        if ((intent.flags and Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT) != 0) {
            finish()
            return
        }
    }

    override fun initView(savedInstanceState: Bundle?) {
        // 设置隐藏导航栏
        StatusBarSettingHelper.setStatusBarTranslucent(this)

        if(TokenManager.getToken().isNullOrEmpty()) {
            oauthToken()
        }

        if (TokenManager.getBaiduToken().isNullOrEmpty()) {
            oauthBaiduToken()
        }

        mBinding.tvSkip.onClick {
            MainServiceProvider.toMain(this)
            finish()
        }

        countDownCoroutines(2, lifecycleScope, onTick = {
            // 跳过按钮显示文字
            mBinding.tvSkip.text = getString(R.string.splash_time, it.plus(1).toString())
        }) {
            MainServiceProvider.toMain(this)
            finish()
        }
    }

    private fun oauthToken() {
        mViewModel.sendAuthRequestClient(
            GRANT_TYPE,
            CLIENT_ID,
            CLIENT_SECRET
        ).observe(this) {
            TokenManager.saveToken(it.access_token)
        }
    }


    private fun oauthBaiduToken() {
        mViewModel.sendBaiduAuthRequestClient(
            API_KEY, SECRET_KEY
        ).observe(this) {
            TokenManager.saveBaiduToken(it.access_token)
        }
    }
}