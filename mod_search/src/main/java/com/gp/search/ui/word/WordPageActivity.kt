package com.gp.search.ui.word

import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.gp.common.constant.SEARCH_ACTIVITY_WORD
import com.gp.framework.base.BaseMvvmActivity
import com.gp.framework.ext.onClick
import com.gp.framework.toast.TipsToast
import com.gp.mod_search.databinding.ActivityWordPageBinding
import com.gp.network.constant.KEY_WORD

@Route(path = SEARCH_ACTIVITY_WORD)
class WordPageActivity : BaseMvvmActivity<ActivityWordPageBinding, WordPageViewModel>() {

    override fun initView(savedInstanceState: Bundle?) {
        TipsToast.showTips(intent.getStringExtra(KEY_WORD))

        initEvent()


    }

    private fun initEvent() {
        mBinding.ivBack.onClick {
            onBackPressed()
        }

        mBinding.ivSuggest.onClick {

        }

    }
}