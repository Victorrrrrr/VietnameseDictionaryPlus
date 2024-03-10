package com.gp.user.ui.language

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.gp.common.constant.USER_ACTIVITY_ABOUT
import com.gp.framework.base.BaseDataBindActivity
import com.gp.mod_user.R
import com.gp.mod_user.databinding.ActivityLanguageBinding

@Route(path = USER_ACTIVITY_ABOUT)
class LanguageActivity : BaseDataBindActivity<ActivityLanguageBinding>() {
    override fun initView(savedInstanceState: Bundle?) {

    }

}