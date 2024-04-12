package com.gp.learn.quick

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.view.animation.Animation
import android.view.animation.LinearInterpolator
import com.alibaba.android.arouter.facade.annotation.Route
import com.gp.common.constant.LEARN_ACTIVITY_QUICK
import com.gp.common.model.LearnWordBean
import com.gp.framework.base.BaseMvvmActivity
import com.gp.framework.ext.gone
import com.gp.framework.ext.onClick
import com.gp.framework.ext.visible
import com.gp.framework.toast.TipsToast
import com.gp.framework.utils.MediaHelper
import com.gp.learn.game.GameViewModel
import com.gp.learn.show.ShowActivity
import com.gp.mod_learn.databinding.ActivityQuickBinding


@Route(path = LEARN_ACTIVITY_QUICK)
class QuickActivity : BaseMvvmActivity<ActivityQuickBinding, GameViewModel>() {

    companion object {
        @JvmField var wordList: ArrayList<LearnWordBean> = ArrayList()
    }

    override fun initView(savedInstanceState: Bundle?) {
        prepareData()
        windowExplode()
        initAnimation()
        initEvent()
    }

    private fun prepareData() {
        showLoading()
        if(wordList.isNotEmpty()) wordList.clear()
        mViewModel.getWordLearn(30) {
            dismissLoading()
            if (it != null) {
                wordList = it
                showWord()
                playWord()
            }
        }.observe(this) {}
    }

    private var objectAnimator: ObjectAnimator? = null



    private var mediaPlayer: MediaPlayer? = null

    private var tem = 0

    private var isPause = false



    private fun initAnimation() {
        objectAnimator = ObjectAnimator.ofFloat(mBinding.cardSpCircle, "rotation", 0.0f, 360.0f)
            .apply {
                duration = 25000
                repeatCount = Animation.INFINITE
                repeatMode = ObjectAnimator.RESTART
                interpolator = LinearInterpolator()
            }
        objectAnimator?.start()
    }



    private fun initEvent() {
        mBinding.layoutSpPause.onClick {
            if(!isPause) {
                isPause = true
                objectAnimator!!.pause()
                mBinding.textSpPause.text = "继续"
            } else {
                isPause = false
                objectAnimator!!.resume()
                mBinding.textSpPause.text = "暂停"
                tem++
                playWord()
            }
        }

        mBinding.layoutSpHome.onClick { onBackPressed() }
        mBinding.layoutSpWord.onClick {
            mBinding.cardSpCircle.visible()
            mBinding.layoutSpWord.gone()
        }
        mBinding.cardSpCircle.onClick {
            mBinding.cardSpCircle.gone()
            mBinding.layoutSpWord.visible()
        }
    }


    // 播放单词
    private fun playWord() {
        if (mediaPlayer != null) {
            mediaPlayer!!.release()
            mediaPlayer = null
            mediaPlayer = MediaPlayer()
        } else mediaPlayer = MediaPlayer()
        try {
            mediaPlayer!!.setDataSource(wordList[tem].pronounceVi)
            mediaPlayer!!.prepareAsync()
            mediaPlayer!!.setOnPreparedListener {
                if (!isPause) {
                    mediaPlayer!!.start()
                    showWord()
                }
            }
            mediaPlayer!!.setOnCompletionListener {
                if (tem != wordList.size - 1) {
                    if (!isPause) {
                        Handler().postDelayed(Runnable {
                            ++tem
                            playWord()
                        }, 1000)
                    }
                } else {
                    TipsToast.showTips("播放完毕")
                    val intent = Intent()
                    intent.setClass(this@QuickActivity, ShowActivity::class.java)
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    intent.putExtra(ShowActivity.SHOW_TYPE, ShowActivity.TYPE_SPEED)
                    startActivity(intent)
                }
            }
            mediaPlayer!!.setOnErrorListener { mp, what, extra ->
                TipsToast.showTips( "发生错误，即将返回，请检查联网设置")
                onBackPressed()
                true
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    @SuppressLint("SetTextI18n")
    private fun showWord() {
        if(wordList.isEmpty()) return
        mBinding.textSpTop.text = (tem + 1).toString() + " / " + wordList.size
        mBinding.textLsWord.text = wordList[tem].wordVi
//        mBinding.textLsPhone.text = wordList[tem]
        mBinding.textLsMean.text = wordList[tem].pos + " " + wordList[tem].wordZh
    }

    override fun onBackPressed() {
        super.onBackPressed()
        isPause = true
        if (mediaPlayer != null) {
            mediaPlayer!!.release()
            mediaPlayer = null
        }
    }





}