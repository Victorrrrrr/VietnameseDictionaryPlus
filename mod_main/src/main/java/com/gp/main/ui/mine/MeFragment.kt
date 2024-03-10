package com.gp.main.ui.mine

import android.content.Context
import android.os.Bundle
import android.view.View
import com.gp.common.provider.LoginServiceProvider
import com.gp.common.provider.UserServiceProvider
import com.gp.framework.base.BaseMvvmFragment
import com.gp.framework.ext.onClick
import com.gp.main.databinding.FragmentMeBinding

class MeFragment : BaseMvvmFragment<FragmentMeBinding, MeViewModel>() {
    override fun initView(view: View, savedInstanceState: Bundle?) {

        initEvent()


    }

    private fun initEvent() {
        mBinding?.rlAbout?.onClick {
            UserServiceProvider.toAbout(it.context)
        }

        mBinding?.rlBookWord?.onClick {
            UserServiceProvider.toWordBook(it.context)
        }

        mBinding?.rlChangeLanguage?.onClick {
            UserServiceProvider.toLanguage(it.context)
        }

        mBinding?.rlFavWord?.onClick {
            UserServiceProvider.toCollection(it.context)
        }

        mBinding?.rlNotificationWord?.onClick {
            UserServiceProvider.toNotification(it.context)
        }

        mBinding?.rlSearchWordHistory?.onClick {
            UserServiceProvider.toHistory(it.context)
        }

        mBinding?.ivHeadImg?.onClick {
            toUserOrLogin(it.context)
        }

        mBinding?.tvUserName?.onClick {
            toUserOrLogin(it.context)
        }


        mBinding?.rlDarkMode?.onClick {
            // 切换switch

        }

    }


    private fun toUserOrLogin(context: Context) {
        // 判断是否登陆，未登陆跳转登陆页
        if(LoginServiceProvider.isLogin()) {
            UserServiceProvider.toUserInfo(context)
        } else {
            LoginServiceProvider.toLogin(context)
        }
    }

}