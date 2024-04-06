package com.gp.learn.review

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.gp.common.constant.LEARN_ACTIVITY_MATCH
import com.gp.framework.base.BaseMvvmActivity
import com.gp.mod_learn.R
import com.gp.mod_learn.databinding.ActivityWordMatchBinding

@Route(path = LEARN_ACTIVITY_MATCH)
class WordMatchActivity : BaseMvvmActivity<ActivityWordMatchBinding, WordMatchViewModel>() {

    override fun initView(savedInstanceState: Bundle?) {

    }
}