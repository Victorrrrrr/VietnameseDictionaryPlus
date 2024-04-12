package com.gp.main.ui.mine

import android.content.Context
import android.os.Bundle
import android.view.View
import com.gp.common.manager.UserInfoManager
import com.gp.common.provider.LoginServiceProvider
import com.gp.common.provider.UserServiceProvider
import com.gp.framework.base.BaseMvvmFragment
import com.gp.framework.ext.gone
import com.gp.framework.ext.invisible
import com.gp.framework.ext.onClick
import com.gp.framework.ext.visible
import com.gp.framework.toast.TipsToast
import com.gp.framework.utils.getStringFromResource
import com.gp.main.R
import com.gp.main.databinding.FragmentMeBinding
import com.gp.network.manager.TokenManager
import com.gp.network.manager.WordBookIdManager

class MeFragment : BaseMvvmFragment<FragmentMeBinding, MeViewModel>() {

    override fun initView(view: View, savedInstanceState: Bundle?) {
        initEvent()
    }

    override fun onResume() {
        super.onResume()
        if(LoginServiceProvider.isLogin()) {
            mBinding?.tvUserName?.text = UserInfoManager.getUserName()
            mBinding?.ivLogout?.visible()

            mViewModel.getProcess(WordBookIdManager.getWordBookId()).observe(this) {
                mBinding?.tvWordNumSum?.text = String.format(
                    getStringFromResource(com.gp.lib_widget.R.string.me_word_learned_sum), it.process.toString()
                )
            }
        } else {
            mBinding?.tvWordNumSum?.text = getStringFromResource(com.gp.lib_widget.R.string.me_word_learned_no_login)

        }

    }

    private fun initEvent() {
        mBinding?.rlAbout?.onClick {
            UserServiceProvider.toAbout(it.context)
        }

        mBinding?.rlBookWord?.onClick {
            if(LoginServiceProvider.isLogin()) {
                UserServiceProvider.toWordBook(it.context)
            } else {
                TipsToast.showTips(getStringFromResource(com.gp.lib_widget.R.string.no_login_tips))
            }
        }

        mBinding?.rlChangeLanguage?.onClick {
            UserServiceProvider.toLanguage(it.context)
        }

        mBinding?.rlFavWord?.onClick {
            if(LoginServiceProvider.isLogin()) {
                UserServiceProvider.toCollection(it.context)
            } else {
                TipsToast.showTips(getStringFromResource(com.gp.lib_widget.R.string.no_login_tips))
            }
        }

        mBinding?.rlNotificationWord?.onClick {
            UserServiceProvider.toNotification(it.context)
        }

        mBinding?.rlSearchWordHistory?.onClick {
            if(LoginServiceProvider.isLogin()){
                UserServiceProvider.toHistory(it.context)
            } else {
                TipsToast.showTips(getStringFromResource(com.gp.lib_widget.R.string.no_login_tips))
            }
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

        // 退出登录按钮
        mBinding?.ivLogout?.onClick {
            UserInfoManager.clearAll()
            TokenManager.clearToken()
            WordBookIdManager.saveFavFolderId(-1)

            mBinding?.tvUserName?.text = getStringFromResource(com.gp.lib_widget.R.string.me_login_tip_text)
            it.gone()
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