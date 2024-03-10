package com.gp.login.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.gp.common.constant.LOGIN_ACTIVITY_LOGIN
import com.gp.framework.base.BaseMvvmActivity
import com.gp.framework.ext.onClick
import com.gp.framework.toast.TipsToast
import com.gp.login.register.RegisterActivity
import com.gp.mod_login.databinding.ActivityLoginBinding
import com.gp.network.manager.TokenManager
import com.gp.framework.utils.getStringFromResource
import com.gp.lib_widget.R


@Route(path = LOGIN_ACTIVITY_LOGIN)
class LoginActivity : BaseMvvmActivity<ActivityLoginBinding, LoginViewModel>() {
    override fun initView(savedInstanceState: Bundle?) {

        initEvent()

    }

    private fun initEvent() {
        mBinding.toolbarLogin.setNavigationOnClickListener {
            onBackPressed()
        }

        mBinding.btnRegister.onClick {
            startActivity(Intent(this@LoginActivity, RegisterActivity::class.java))
        }

        mBinding.btnLogin.onClick {
            if (!mBinding.cbAgree.isChecked) {
                TipsToast.showTips(getStringFromResource(R.string.agree_privacy_tip))
                return@onClick
            }


            val username = mBinding.etUsername.text.toString()
            val pwd = mBinding.etPwd.text.toString()

            if(username.isNotEmpty() && pwd.isNotEmpty()) {
                mViewModel.sendAuthRequestPassword(username, pwd)
                    .observe(this) {
                        TokenManager.saveToken(it.accessToken)
                        // TODO 返回主页刷新数据等操作
                    }
            } else {
                TipsToast.showTips("请完整填写以上信息，保证格式正确")
            }


        }
    }


}