package com.gp.main.ui

import android.animation.ValueAnimator
import android.content.Intent
import android.content.res.Configuration
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.gp.common.constant.*
import com.gp.common.model.FolderAddRequest
import com.gp.common.provider.MainServiceProvider
import com.gp.framework.base.BaseMvvmActivity
import com.gp.framework.ext.countDownCoroutines
import com.gp.framework.ext.onClick
import com.gp.framework.utils.DarkThemeChangeUtils
import com.gp.framework.utils.MMKVUtil
import com.gp.framework.utils.MMKV_TYPE
import com.gp.framework.toast.TipsToast
import com.gp.framework.utils.LogUtil
import com.gp.lib_framework.utils.StatusBarSettingHelper
import com.gp.lib_widget.R

import com.gp.main.databinding.ActivitySplashBinding
import com.gp.main.ui.main.MainViewModel
import com.gp.network.manager.TokenManager
import java.util.Locale

class SplashActivity : BaseMvvmActivity<ActivitySplashBinding, MainViewModel>() {

    override fun preCreate() {
        // 避免每次启动都走splash界面，针对某米手机
        if ((intent.flags and Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT) != 0) {
            finish()
            return
        }
    }

    override fun initView(savedInstanceState: Bundle?) {
        autoSetDarkMode()
        // 设置隐藏导航栏
        StatusBarSettingHelper.setStatusBarTranslucent(this)

        if (TokenManager.getToken().isNullOrEmpty()) {
            oauthToken()
        }

        if (TokenManager.getBaiduToken().isNullOrEmpty()) {
            oauthBaiduToken()
        }

        mBinding.tvSkip.onClick {
            MainServiceProvider.toMain(this)
            finish()
        }

        val animator = ValueAnimator.ofFloat(0F,1F)
        animator.setDuration(1800)
        animator.addUpdateListener {
            val alpha = it.animatedValue as Float
            mBinding.tvAppName.alpha = alpha
        }
        animator.start()


        countDownCoroutines(3, lifecycleScope, onTick = {
            // 跳过按钮显示文字
            mBinding.tvSkip.text = getString(R.string.splash_time, it.plus(1).toString())
        }) {
            MainServiceProvider.toMain(this)
            finish()
        }
    }

    /**
     * 设置是否是暗色模式
     */
    private fun autoSetDarkMode() {
        StatusBarSettingHelper.setStatusBarTranslucent(this)
        MMKVUtil.get(MMKV_TYPE.APP).decodeBoolean("IS_NIGHT_MODE")
            ?.let {
                StatusBarSettingHelper.statusBarLightMode(this, !it)
                DarkThemeChangeUtils.autoSetDayAndNightMode(this)
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