package com.gp.learn.tool.voice_trans

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.gp.common.constant.LEARN_ACTIVITY_VOICE
import com.gp.framework.base.BaseDataBindActivity
import com.gp.mod_learn.R
import com.gp.mod_learn.databinding.ActivityVoiceTransBinding


@Route(path = LEARN_ACTIVITY_VOICE)
class VoiceTransActivity : BaseDataBindActivity<ActivityVoiceTransBinding>() {

    override fun initView(savedInstanceState: Bundle?) {

    }
}