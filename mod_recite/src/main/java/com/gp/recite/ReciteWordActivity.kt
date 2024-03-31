package com.gp.recite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.gp.common.constant.RECITE_ACTIVITY_RECITE
import com.gp.common.provider.MainServiceProvider
import com.gp.framework.base.BaseMvvmActivity
import com.gp.framework.ext.click
import com.gp.mod_recite.databinding.ActivityReciteWordBinding
import com.gp.recite.viewmodel.ReciteWordViewModel

@Route(path = RECITE_ACTIVITY_RECITE)
class ReciteWordActivity : BaseMvvmActivity<ActivityReciteWordBinding, ReciteWordViewModel>() {

    override fun initView(savedInstanceState: Bundle?) {

        mBinding.ivToHome.click {
            MainServiceProvider.toMain(this, 0)
        }

    }


    override fun onBackPressed() {
        MainServiceProvider.toMain(this@ReciteWordActivity)
        // TODO 释放音频

        finish()
    }

}