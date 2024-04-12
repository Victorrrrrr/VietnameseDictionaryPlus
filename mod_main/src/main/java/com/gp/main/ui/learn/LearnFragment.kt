package com.gp.main.ui.learn

import android.os.Bundle
import android.view.View
import com.gp.common.provider.LearnServiceProvider
import com.gp.common.provider.LoginServiceProvider
import com.gp.framework.base.BaseMvvmFragment
import com.gp.framework.ext.onClick
import com.gp.framework.toast.TipsToast
import com.gp.framework.utils.getStringFromResource
import com.gp.lib_widget.R
import com.gp.main.databinding.FragmentLearnBinding


class LearnFragment : BaseMvvmFragment<FragmentLearnBinding, LearnViewModel>() {
    override fun initView(view: View, savedInstanceState: Bundle?) {
        initEvent()
    }

    private fun initEvent() {
        mBinding?.rlChasingGame?.onClick {
            if (LoginServiceProvider.isLogin()) {
                LearnServiceProvider.toGame(it.context)
            } else {
                TipsToast.showTips(getStringFromResource(R.string.no_login_tips))
            }
        }

        mBinding?.rlVoiceTransBtn?.onClick {
            TipsToast.showTips("暂未实现")
            return@onClick
            LearnServiceProvider.toVoiceTrans(it.context)
        }

        mBinding?.rlWordCollection?.onClick {
            if(LoginServiceProvider.isLogin()) {
                LearnServiceProvider.toNote(it.context)
            } else {
                TipsToast.showTips(getStringFromResource(R.string.no_login_tips))
            }
        }

        mBinding?.rlWordsMatching?.onClick {
            if (LoginServiceProvider.isLogin()) {
                LearnServiceProvider.toWordMatch(it.context)
            } else {
                TipsToast.showTips(getStringFromResource(R.string.no_login_tips))
            }
        }

        mBinding?.rlWordsQuickPass?.onClick {
            if (LoginServiceProvider.isLogin()) {
                LearnServiceProvider.toQuickPass(it.context)
            } else {
                TipsToast.showTips(getStringFromResource(R.string.no_login_tips))
            }
        }

    }


}