package com.gp.user.ui.about

import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.gp.common.constant.USER_ACTIVITY_ABOUT
import com.gp.framework.base.BaseDataBindActivity

import com.gp.mod_user.databinding.ActivityAboutBinding

@Route(path = USER_ACTIVITY_ABOUT)
class AboutActivity : BaseDataBindActivity<ActivityAboutBinding>() {
    override fun initView(savedInstanceState: Bundle?) {

    }

}