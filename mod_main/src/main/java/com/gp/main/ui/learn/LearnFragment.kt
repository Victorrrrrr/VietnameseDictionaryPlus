package com.gp.main.ui.learn

import android.os.Bundle
import android.view.View
import com.gp.common.provider.LearnServiceProvider
import com.gp.framework.base.BaseMvvmFragment
import com.gp.framework.ext.onClick
import com.gp.main.databinding.FragmentLearnBinding


class LearnFragment : BaseMvvmFragment<FragmentLearnBinding, LearnViewModel>() {
    override fun initView(view: View, savedInstanceState: Bundle?) {
        initEvent()
    }

    private fun initEvent() {
        mBinding?.rlChasingGame?.onClick {
            LearnServiceProvider.toGame(it.context)
        }

        mBinding?.rlVoiceTransBtn?.onClick {
            LearnServiceProvider.toVoiceTrans(it.context)
        }

        mBinding?.rlWordCollection?.onClick {
            LearnServiceProvider.toNote(it.context)
        }

        mBinding?.rlWordsMatching?.onClick {
            LearnServiceProvider.toWordMatch(it.context)
        }

    }


}