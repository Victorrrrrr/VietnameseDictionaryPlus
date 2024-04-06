package com.gp.login.register

import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.gp.common.constant.LOGIN_ACTIVITY_REGISTER
import com.gp.framework.base.BaseMvvmActivity
import com.gp.framework.ext.onClick
import com.gp.framework.toast.TipsToast
import com.gp.framework.utils.getStringFromResource
import com.gp.login.login.LoginViewModel
import com.gp.mod_login.databinding.ActivityRegisterBinding


@Route(path = LOGIN_ACTIVITY_REGISTER)
class RegisterActivity : BaseMvvmActivity<ActivityRegisterBinding, LoginViewModel>() {
    override fun initView(savedInstanceState: Bundle?) {
        initEvent()
    }

    private fun initEvent() {
        mBinding.toolbarRegister.setNavigationOnClickListener {
            onBackPressed()
        }

        // 注册按钮逻辑
        mBinding.btnRegister.onClick {
            if (!mBinding.cbAgree.isChecked) {
                TipsToast.showTips(getStringFromResource(com.gp.lib_widget.R.string.agree_privacy_tip))
                return@onClick
            }

            val username = mBinding.etUsername.text.toString()
            val pwd = mBinding.etPwd.text.toString()
            val email = mBinding.etEmail.text.toString()

            val allNotEmpty = username.isNotEmpty() && pwd.isNotEmpty() && email.isNotEmpty()

            if(allNotEmpty) {
                showLoading("注册中，请稍后")
                mViewModel.sendRegisterRequest(username, pwd, email)?.observe(this) {
                    dismissLoading()
                    TipsToast.showTips("注册成功")
                    finish()
                }
            } else {
                TipsToast.showTips("请完整填写以上信息，保证格式正确")
            }
        }
    }

}