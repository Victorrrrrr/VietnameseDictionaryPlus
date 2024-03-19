package com.gp.main.ui.daily.person

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.gp.framework.base.BaseMvvmActivity
import com.gp.framework.ext.onClick
import com.gp.lib_framework.utils.StatusBarSettingHelper
import com.gp.main.R
import com.gp.main.databinding.ActivityDailyPersonBinding

class DailyPersonActivity :BaseMvvmActivity<ActivityDailyPersonBinding, DailyPersonViewModel>(){
    override fun initView(savedInstanceState: Bundle?) {
        StatusBarSettingHelper.setStatusBarTranslucent(this)

        mBinding.tvMoreBtn.onClick {
            startActivity(Intent(this, PersonDetailActivity::class.java))
        }



    }

}