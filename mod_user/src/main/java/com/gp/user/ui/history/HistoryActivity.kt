package com.gp.user.ui.history

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.gp.common.constant.USER_ACTIVITY_HISTORY
import com.gp.framework.base.BaseMvvmActivity
import com.gp.mod_user.R
import com.gp.mod_user.databinding.ActivityHistoryBinding

@Route(path = USER_ACTIVITY_HISTORY)
class HistoryActivity : BaseMvvmActivity<ActivityHistoryBinding, HistoryViewModel>() {
    override fun initView(savedInstanceState: Bundle?) {

    }

}