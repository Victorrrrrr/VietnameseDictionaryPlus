package com.gp.user.ui.about

import android.os.Bundle
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.gp.common.constant.USER_ACTIVITY_ABOUT
import com.gp.framework.base.BaseDataBindActivity
import com.gp.framework.utils.getColorFromResource
import com.gp.lib_framework.utils.StatusBarSettingHelper
import com.gp.lib_widget.R

import com.gp.mod_user.databinding.ActivityAboutBinding

@Route(path = USER_ACTIVITY_ABOUT)
class AboutActivity : BaseDataBindActivity<ActivityAboutBinding>() {
    override fun initView(savedInstanceState: Bundle?) {
        // 设置状态栏背景色
        getWindow().setStatusBarColor(getColorFromResource(R.color.vd_theme_color));
        // 设置状态栏字体白色
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE)

        mBinding.toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
    }

}