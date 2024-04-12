package com.gp.recite

import android.os.Bundle
import com.gp.common.provider.MainServiceProvider
import com.gp.framework.base.BaseMvvmActivity
import com.gp.framework.ext.onClick
import com.gp.framework.utils.getStringFromResource
import com.gp.lib_framework.utils.StatusBarSettingHelper
import com.gp.mod_recite.R
import com.gp.mod_recite.databinding.ActivityFinishBinding
import com.gp.network.manager.WordBookIdManager
import com.gp.recite.viewmodel.ReciteWordsViewModel


class FinishActivity : BaseMvvmActivity<ActivityFinishBinding, ReciteWordsViewModel>(){

    override fun initView(savedInstanceState: Bundle?) {
        StatusBarSettingHelper.setStatusBarTranslucent(this)

        val wordAddNum = WordBookIdManager.getReciteNum()
        val wordProcessSum = WordBookIdManager.getWordBookNum()
        val wordBookId = WordBookIdManager.getWordBookId()

        mViewModel.getProcess(wordBookId).observe(this) {
            mBinding.tvWordProcess.text = String.format(getStringFromResource(com.gp.lib_widget.R.string.finish_process_word), it.process, wordProcessSum)
        }

        mBinding.apply {
            tvWordAddNum.text = String.format(getStringFromResource(com.gp.lib_widget.R.string.finish_learn_word_tip), wordAddNum.toString())


            btnFiBack.onClick {
                onBackPressed()
            }
        }

    }


    override fun onBackPressed() {
        MainServiceProvider.toMain(this@FinishActivity, 0)
    }
}