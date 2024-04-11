package com.gp.learn.quick

import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.gp.common.constant.LEARN_ACTIVITY_QUICK
import com.gp.framework.base.BaseMvvmActivity
import com.gp.learn.game.GameViewModel
import com.gp.mod_learn.databinding.ActivityQuickBinding

@Route(path = LEARN_ACTIVITY_QUICK)
class QuickActivity : BaseMvvmActivity<ActivityQuickBinding, GameViewModel>() {
    override fun initView(savedInstanceState: Bundle?) {

    }

}