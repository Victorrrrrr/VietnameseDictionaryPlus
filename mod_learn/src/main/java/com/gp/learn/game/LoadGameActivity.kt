package com.gp.learn.game

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.gp.common.constant.LEARN_ACTIVITY_GAME
import com.gp.framework.base.BaseDataBindActivity
import com.gp.mod_learn.R
import com.gp.mod_learn.databinding.ActivityLoadGameBinding

@Route(path = LEARN_ACTIVITY_GAME)
class LoadGameActivity : BaseDataBindActivity<ActivityLoadGameBinding>() {
    override fun initView(savedInstanceState: Bundle?) {

    }

}