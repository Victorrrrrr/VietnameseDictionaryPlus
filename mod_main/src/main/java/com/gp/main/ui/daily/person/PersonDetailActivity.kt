package com.gp.main.ui.daily.person

import android.os.Bundle
import com.gp.common.provider.MainServiceProvider
import com.gp.framework.base.BaseMvvmActivity
import com.gp.framework.ext.onClick
import com.gp.main.databinding.ActivityPersonDetailBinding

class PersonDetailActivity : BaseMvvmActivity<ActivityPersonDetailBinding, DailyPersonViewModel>() {
    override fun initView(savedInstanceState: Bundle?) {
        mBinding.toolbarDailyDetail.setNavigationOnClickListener { onBackPressed() }
        mBinding.btnHome.onClick {
            MainServiceProvider.toMain(this, 0)
        }
    }
}