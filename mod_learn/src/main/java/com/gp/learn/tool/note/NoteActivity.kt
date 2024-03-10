package com.gp.learn.tool.note

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.gp.common.constant.LEARN_ACTIVITY_NOTE
import com.gp.framework.base.BaseMvvmActivity
import com.gp.mod_learn.R
import com.gp.mod_learn.databinding.ActivityNoteBinding



@Route(path = LEARN_ACTIVITY_NOTE)
class NoteActivity : BaseMvvmActivity<ActivityNoteBinding, NoteViewModel>() {
    override fun initView(savedInstanceState: Bundle?) {

    }
}