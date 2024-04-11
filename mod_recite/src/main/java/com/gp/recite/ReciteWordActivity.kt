package com.gp.recite

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.android.arouter.facade.annotation.Route
import com.gp.common.constant.KEY_INDEX
import com.gp.common.constant.RECITE_ACTIVITY_RECITE
import com.gp.common.model.LearnWordBean
import com.gp.common.provider.MainServiceProvider
import com.gp.common.provider.SearchServiceProvider
import com.gp.framework.base.BaseMvvmActivity
import com.gp.framework.ext.onClick
import com.gp.framework.ext.toJson
import com.gp.framework.utils.LogUtil
import com.gp.framework.utils.MediaHelper
import com.gp.framework.utils.getStringFromResource
import com.gp.lib_framework.utils.StatusBarSettingHelper
import com.gp.mod_recite.databinding.ActivityReciteWordBinding
import com.gp.mod_recite.databinding.ViewStubChooseMeaningBinding
import com.gp.recite.controller.WordController
import com.gp.recite.viewmodel.ReciteWordsViewModel


@Route(path = RECITE_ACTIVITY_RECITE)
class ReciteWordActivity : BaseMvvmActivity<ActivityReciteWordBinding, ReciteWordsViewModel>() {

    companion object{
        @JvmField var learningIndex = 0
        @JvmField var reviseIndex = 0
        @JvmField var lastLearningIndex = 0
        @JvmField var lastReviseIndex = 0
        @JvmField var totalTimeIndex = 0

        const val CHANGE_TIMES = 2

        @JvmField var learnMode : LearnMode? = NewLearnMode
        @JvmField var wrongModeContinue = true

        @JvmField var wordListFromFolder : List<LearnWordBean>? = null

        fun start(context: Context, list: List<LearnWordBean>?) {
            val intent = Intent(context, ReciteWordActivity::class.java)
            this.wordListFromFolder = list
            context.startActivity(intent)
        }

    }

    private var wordList : List<LearnWordBean>? = null
    private var wordNum : Int = 0

    private var meaningAdapter : MeaningOptionAdapter = MeaningOptionAdapter()

    private var viewStubBinding : ViewStubChooseMeaningBinding? = null

    private var wrongDeque : ArrayDeque<LearnWordBean> = ArrayDeque()

    private var viewStub : View? = null


    override fun initView(savedInstanceState: Bundle?) {
        StatusBarSettingHelper.setStatusBarTranslucent(this)
        resetDefaultParam()

        wordList = WordController.getWordList()
        LogUtil.d(tag = "word", message = wordList?.toJson().toString())

        wordList?.let {
            wordNum = it.size
        }
        updateStatus()

        initEvent()

    }

    private fun resetDefaultParam() {
        learningIndex = 0
        reviseIndex = 0
        lastLearningIndex = 0
        lastReviseIndex = 0
        totalTimeIndex = 0
        learnMode = NewLearnMode
    }

    private fun initEvent() {
        mBinding.ivToHome.onClick {
            MainServiceProvider.toMain(this, 0)
        }

        mBinding.tvKnow.onClick {
            if(mBinding.tvEn.visibility == View.VISIBLE) {
                mBinding.tvEn.visibility = View.GONE
            }
            if(mBinding.tvPos.visibility == View.VISIBLE) {
                mBinding.tvPos.visibility = View.GONE
            }

            if(learningIndex == 0) {
                mBinding.tvLearnProcess.visibility = View.VISIBLE
            }

            // 跳转下一个，更新数据
            learningIndex++
            updateStatus()
        }

        mBinding.tvUnclear.onClick {
            wordList?.get(learningIndex)?.pronounceVi?.let {
                MediaHelper.play(it)
            }

            // 如果英语不为空，显示英语
            wordList?.get(learningIndex)?.wordEn?.let {
                if(it.isNotEmpty()) {
                    mBinding.tvEn.visibility = View.VISIBLE
                    mBinding.tvEn.text = String.format(
                        getStringFromResource(com.gp.lib_widget.R.string.recite_tv_en),
                        it
                    )
                }
            }

            wordList?.get(learningIndex)?.pos?.let {
                if(it.isNotEmpty()) {
                    mBinding.tvPos.visibility = View.VISIBLE
                    mBinding.tvPos.text = String.format(
                        getStringFromResource(com.gp.lib_widget.R.string.recite_tv_pos),
                        it
                    )
                }
            }
        }

        mBinding.tvUnknown.onClick {
            val id = wordList?.get(learningIndex)?.id
            SearchServiceProvider.toWordPage(this, id.toString())
        }

    }


    fun showLearnBottomTab() {
        mBinding.tvKnow.visibility = View.VISIBLE
        mBinding.tvUnclear.visibility = View.VISIBLE
        mBinding.tvUnknown.visibility = View.VISIBLE
    }

    fun hideLearnBottomTab() {
        mBinding.tvKnow.visibility = View.GONE
        mBinding.tvUnclear.visibility = View.GONE
        mBinding.tvUnknown.visibility = View.GONE
    }

    private fun updateStatus() {
        mBinding.newLearnCount = learningIndex.toString()
        mBinding.revise = reviseIndex.toString()
        when(learnMode){
            is NewLearnMode -> {
                if(learningIndex >= wordNum) {
                    // 完成学习
                    learnMode = ReviseMode
                    hideLearnBottomTab()
                    updateStatus()
                    return
                }

                if(learningIndex != lastLearningIndex && learningIndex % CHANGE_TIMES == 0) {
                    learnMode = ReviseMode
                    lastLearningIndex = learningIndex
                    hideLearnBottomTab()
                    updateStatus()
                    return
                }

                showLearnBottomTab()
                mBinding.tvWord.text = wordList?.get(learningIndex)?.wordVi
                wordList?.get(learningIndex)?.pronounceVi?.let {
                    MediaHelper.play(it)
                }

                totalTimeIndex++
            }

            is ReviseMode -> {
                if(reviseIndex >= wordNum) {
                    // 完成复习
                    learnMode = WrongRepeatMode
                    updateStatus()
                    return
                }
                if(shouldChangeMode()){
                    updateStatus()
                    return
                }

                if(reviseIndex == 0) {
                    initOptions()
                } else {
                    showOptions()
                }
                // 执行UI操作
                mBinding.tvWord.text = wordList?.get(reviseIndex)?.wordVi
                wordList?.get(reviseIndex)?.pronounceVi?.let {
                    MediaHelper.play(it)
                }
                meaningAdapter.setData(wordList?.get(reviseIndex)?.options)
                meaningAdapter.setOnOptionListener(object : OnOptionListener {
                    override fun onRight() {
                        Handler().postDelayed({
                            shouldChangeMode()
                            totalTimeIndex++
                            reviseIndex++
                            updateStatus()
                        }, 800)
                    }

                    override fun onFalse() {
                        if(reviseIndex < wordNum) {
                            val wrongWordBean = wordList!![reviseIndex]
                            wrongDeque.add(wrongWordBean)
                            Handler().postDelayed({
                                SearchServiceProvider.toWordPage(mBinding.container.context, wrongWordBean.id.toString())
                                shouldChangeMode()
                                totalTimeIndex++
                                reviseIndex++
                                updateStatus()
                            }, 800)
                        }
                    }

                })
                meaningAdapter.notifyDataSetChanged()
                viewStubBinding?.rvMeaning?.adapter = meaningAdapter
                viewStubBinding?.rvMeaning?.layoutManager = LinearLayoutManager(this)
                viewStubBinding?.ivSound?.onClick {
                    wordList?.get(reviseIndex)?.pronounceVi?.let {
                        MediaHelper.play(it)
                    }
                }

            }


            is WrongRepeatMode -> {
                if (wrongDeque.isNotEmpty()) {
                    val first = wrongDeque.removeFirst()
                    updateDataFromOption(first)
                } else {
                    // 完成错误练习， 跳转完成页
                    var wordIdList = mutableListOf<Int>()
                    for(word in wordList!!) {
                        wordIdList.add(word.id)
                    }

                    mViewModel.finishLearn(wordIdList){
                        Handler().postDelayed({
                            val intent = Intent(this@ReciteWordActivity, FinishActivity::class.java)
                            startActivity(intent)
                            finish()
                        },500)
                    }.observe(this) {}

                }

            }

            else -> {}
        }
    }


    private fun updateDataFromOption(wordBean: LearnWordBean) {
        mBinding.tvWord.text = wordBean.wordVi
        meaningAdapter.setData(wordBean.options)
        meaningAdapter.setOnOptionListener(object : OnOptionListener {
            override fun onRight() {
                Handler().postDelayed({
                    updateStatus()
                }, 800)
            }

            override fun onFalse() {
                wrongDeque.addLast(wordBean)
                Handler().postDelayed({
                    updateStatus()
                    SearchServiceProvider.toWordPage(mBinding.container.context, wordBean.id.toString())
                }, 800)
            }
        })
        meaningAdapter.notifyDataSetChanged()
        viewStubBinding?.rvMeaning?.adapter = meaningAdapter
        viewStubBinding?.rvMeaning?.layoutManager = LinearLayoutManager(this)
        viewStubBinding?.ivSound?.onClick {
            wordBean.pronounceVi.let {
                MediaHelper.play(it)
            }
        }
    }


    private fun shouldChangeMode() : Boolean{
        val shouldChange = reviseIndex != lastReviseIndex && reviseIndex % CHANGE_TIMES == 0
        if(shouldChange) {
            learnMode = NewLearnMode
            lastReviseIndex = reviseIndex
            hideOptions()
        }
        return shouldChange
    }


    private fun initOptions() {
        mBinding.viewStubChooseMeaning.setOnInflateListener { stub, inflated ->
            viewStubBinding = DataBindingUtil.bind(inflated)
        }

        if(!mBinding.viewStubChooseMeaning.isInflated) {
            viewStub = mBinding.viewStubChooseMeaning.viewStub!!.inflate()
        }

    }


    fun showOptions() {
        viewStub?.visibility = View.VISIBLE
    }

    fun hideOptions() {
        viewStub?.visibility = View.GONE
    }


    override fun onBackPressed() {
        MainServiceProvider.toMain(this@ReciteWordActivity)
        // TODO 释放音频

        finish()
    }

}



sealed class LearnMode
data object NewLearnMode : LearnMode()
data object ReviseMode : LearnMode()
data object WrongRepeatMode : LearnMode()