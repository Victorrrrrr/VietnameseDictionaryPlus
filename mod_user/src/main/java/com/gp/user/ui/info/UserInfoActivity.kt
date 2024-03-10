package com.gp.user.ui.info

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.gp.common.constant.USER_ACTIVITY_USERINFO
import com.gp.framework.base.BaseDataBindActivity
import com.gp.mod_user.R
import com.gp.mod_user.databinding.ActivityUserInfoBinding

@Route(path = USER_ACTIVITY_USERINFO)
class UserInfoActivity : BaseDataBindActivity<ActivityUserInfoBinding>() {


    override fun initView(savedInstanceState: Bundle?) {

    }
}