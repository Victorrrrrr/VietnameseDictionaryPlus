package com.gp.login.policy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.gp.common.constant.LOGIN_ACTIVITY_POLICY
import com.gp.framework.base.BaseMvvmActivity
import com.gp.login.login.LoginViewModel
import com.gp.mod_login.R
import com.gp.mod_login.databinding.ActivityPolicyBinding

@Route(path = LOGIN_ACTIVITY_POLICY)
class PolicyActivity :  BaseMvvmActivity<ActivityPolicyBinding, LoginViewModel>() {
    override fun initView(savedInstanceState: Bundle?) {

    }

}