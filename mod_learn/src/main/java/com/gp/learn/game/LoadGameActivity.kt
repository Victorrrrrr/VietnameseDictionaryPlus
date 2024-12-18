package com.gp.learn.game

import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.gp.common.constant.LEARN_ACTIVITY_GAME
import com.gp.common.model.LearnWordBean
import com.gp.common.provider.MainServiceProvider
import com.gp.framework.base.BaseDataBindActivity
import com.gp.framework.base.BaseMvvmActivity
import com.gp.framework.ext.onClick
import com.gp.framework.utils.MediaHelper
import com.gp.lib_framework.utils.StatusBarUtil
import com.gp.mod_learn.R
import com.gp.mod_learn.databinding.ActivityLoadGameBinding

@Route(path = LEARN_ACTIVITY_GAME)
class LoadGameActivity : BaseMvvmActivity<ActivityLoadGameBinding, GameViewModel>() {

    private var num : Int = 200

    private val FINISH : Int = 1

    private var isDone : Boolean = false

    private var handler = Handler() {
        when(it.what) {
            FINISH -> {
                Handler().postDelayed(object : Runnable {
                    override fun run() {
                        mBinding.imgPlay.visibility = View.VISIBLE
                        mBinding.progressLdg.visibility = View.GONE
                        isDone = true
                    }

                }, 2000)
            }
            else -> false
        }
    }

    override fun initView(savedInstanceState: Bundle?) {
        StatusBarUtil.setStatusBarLightMode(this)

        initVideoView()
        initWordList()
        initEvent()

    }

    private fun initEvent() {
        mBinding.imgPlay.onClick {
            MediaHelper.releasePlayer()
            startActivity(Intent(this@LoadGameActivity, GameActivity::class.java))
            finish()
        }
    }


    private fun initVideoView() {
        mBinding.video.setVideoURI(Uri.parse("android.resource://" + packageName + "/" + R.raw.video))
        mBinding.video.setOnPreparedListener { mBinding.video.start() }
        mBinding.video.setOnCompletionListener {
            mBinding.video.start()
        }
    }


    private fun initWordList() {
        mViewModel.getWordLearn(num){
            var list : MutableList<LearnWordBean> = mutableListOf()
            it?.let {
                for (i in 0 until it.size) {
                    list.add(it[i])
                }
            }
            GameActivity.gameWord = list

            // 发送完成消息
            val message = Message()
            message.what = FINISH
            handler.sendMessage(message)
        }.observe(this) {

        }
    }


    override fun onBackPressed() {
        MainServiceProvider.toMain(this, 1)
        super.onBackPressed()
        MediaHelper.releasePlayer()
        finish()
    }

    override fun onDestroy() {
        super.onDestroy()
        mBinding.video.suspend()
    }


    override fun onPause() {
        super.onPause()
        mBinding.video.suspend()
    }

    override fun onStart() {
        super.onStart()
        mBinding.video.resume()
        if(isDone) {
            mBinding.imgPlay.visibility = View.VISIBLE
            mBinding.progressLdg.visibility = View.GONE
        } else {
            mBinding.imgPlay.visibility = View.GONE
            mBinding.progressLdg.visibility = View.VISIBLE
        }
    }


}