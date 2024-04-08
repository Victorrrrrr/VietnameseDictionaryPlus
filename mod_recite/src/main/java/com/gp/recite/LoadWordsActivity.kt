package com.gp.recite

import android.app.ActivityOptions
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import com.alibaba.android.arouter.facade.annotation.Route
import com.gp.common.constant.KEY_INDEX
import com.gp.common.constant.RECITE_ACTIVITY_LOAD
import com.gp.common.provider.MainServiceProvider
import com.gp.framework.base.BaseMvvmActivity
import com.gp.lib_framework.utils.StatusBarSettingHelper
import com.gp.mod_recite.databinding.ActivityLoadWordsBinding
import com.gp.network.manager.WordBookIdManager
import com.gp.recite.controller.WordController
import com.gp.recite.viewmodel.ReciteWordsViewModel
import kotlinx.coroutines.Runnable

@Route(path = RECITE_ACTIVITY_LOAD)
class LoadWordsActivity : BaseMvvmActivity<ActivityLoadWordsBinding, ReciteWordsViewModel>() {


    private var progressRate : Int = 0
    private var runnable : Runnable? = null
    private var handler : Handler? = null

    companion object {

        fun start(context: Context, index: Int = 0) {
            val intent = Intent(context, LoadWordsActivity::class.java)
            intent.putExtra(KEY_INDEX, index)
            context.startActivity(intent)
        }
    }


    override fun initView(savedInstanceState: Bundle?) {
        StatusBarSettingHelper.setStatusBarTranslucent(this)



    }

    override fun initData() {
        val count = WordBookIdManager.getReciteNum()
        mViewModel.getWordLearn(count).observe(this) {
            WordController.saveWordList(it)
        }
        handler = Handler()

        runnable = object : Runnable {
            override fun run() {
                handler?.postDelayed(this, 10)
                mBinding.progressWait.progress = ++progressRate
                if( progressRate == 100 ) {
                    stopTime()
                    startActivity(
                        Intent(
                            this@LoadWordsActivity,
                            ReciteWordActivity::class.java)
                        ,
                        ActivityOptions.makeSceneTransitionAnimation(
                            this@LoadWordsActivity
                        ).toBundle()
                    )
                }
            }
        }

        handler?.postDelayed(runnable as Runnable, 120)


    }

    private fun stopTime() {
        runnable?.let {
            handler?.removeCallbacks(it)
        }
    }

    override fun onBackPressed() {
        stopTime()
        MainServiceProvider.toMain(this)
    }

    override fun onDestroy() {
        super.onDestroy()
    }

}