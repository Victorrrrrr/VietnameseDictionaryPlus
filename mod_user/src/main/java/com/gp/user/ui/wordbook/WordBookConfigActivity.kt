package com.gp.user.ui.wordbook

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.gp.common.constant.USER_ACTIVITY_WORDBOOK
import com.gp.framework.base.BaseMvvmActivity
import com.gp.mod_user.R
import com.gp.mod_user.databinding.ActivityWordBookConfigBinding

@Route(path = USER_ACTIVITY_WORDBOOK)
class WordBookConfigActivity : BaseMvvmActivity<ActivityWordBookConfigBinding, WordBookViewModel>() {

    override fun initView(savedInstanceState: Bundle?) {

    }
}