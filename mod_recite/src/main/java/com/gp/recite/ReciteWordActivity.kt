package com.gp.recite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewStub
import android.view.ViewStub.OnInflateListener
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.android.arouter.facade.annotation.Route
import com.gp.common.constant.RECITE_ACTIVITY_RECITE
import com.gp.common.model.LearnWordBean
import com.gp.common.provider.MainServiceProvider
import com.gp.common.provider.SearchServiceProvider
import com.gp.framework.base.BaseMvvmActivity
import com.gp.framework.ext.onClick
import com.gp.framework.utils.MediaHelper
import com.gp.framework.utils.getStringFromResource
import com.gp.lib_framework.utils.StatusBarSettingHelper
import com.gp.mod_recite.R
import com.gp.mod_recite.databinding.ActivityReciteWordBinding
import com.gp.mod_recite.databinding.ViewStubChooseMeaningBinding
import com.gp.recite.controller.WordController
import com.gp.recite.viewmodel.ReciteWordsViewModel


@Route(path = RECITE_ACTIVITY_RECITE)
class ReciteWordActivity : BaseMvvmActivity<ActivityReciteWordBinding, ReciteWordsViewModel>() {

    companion object{
        @JvmField var learningIndex = -1
        @JvmField var reviseIndex = -1
        @JvmField var lastLearningIndex = -1
        @JvmField var totalTimeIndex = 0

        const val CHANGE_TIMES = 2
    }

    private var wordList : List<LearnWordBean>? = null
    private var wordNum : Int = 0

    private var meaningAdapter : MeaningOptionAdapter = MeaningOptionAdapter()

    private var learnMode : LearnMode? = NewLearnMode()


    private var viewStubBinding : ViewStubChooseMeaningBinding? = null


    override fun initView(savedInstanceState: Bundle?) {
        StatusBarSettingHelper.setStatusBarTranslucent(this)

        wordList = WordController.getWordList()

        wordList?.let {
            wordNum = it.size
        }
        updateStatus()


        initEvent()


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
            // 跳转下一个，更新数据
            updateStatus()
        }

        mBinding.tvUnclear.onClick {
            if(lastLearningIndex == learningIndex) return@onClick
            wordList?.get(learningIndex)?.pronounceVi?.let {
                MediaHelper.play(it)
            }

            // 如果英语不为空，现实英语
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
            lastLearningIndex = learningIndex
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

    fun updateStatus() {
        when(learnMode){
            is NewLearnMode -> {
                if(++learningIndex >= wordNum) {
                    hideLearnBottomTab()
                    // 完成学习
                    return
                }
                showLearnBottomTab()

                mBinding.tvWord.text = wordList?.get(learningIndex)?.wordVi

                if(totalTimeIndex != 0 && totalTimeIndex % CHANGE_TIMES == 0) {
                    learnMode = ReviseMode()
                    hideLearnBottomTab()
                    updateStatus()
                }

                totalTimeIndex++
            }

            is ReviseMode -> {
                if(++reviseIndex >= wordNum) {
                    // 完成复习

                    return
                }

                if(reviseIndex == 0) {
                    initOptions()
                } else {
                    showOptions()
                }
                // 执行UI操作

                meaningAdapter.setData(wordList?.get(reviseIndex)?.options)
                meaningAdapter.setOnOptionListener(object : OnOptionListener {
                    override fun onRight() {

                    }

                    override fun onFalse() {

                    }

                })
                meaningAdapter.notifyDataSetChanged()
                viewStubBinding?.rvMeaning?.adapter = meaningAdapter
                viewStubBinding?.rvMeaning?.layoutManager = LinearLayoutManager(this)

                // TODO: 几个按钮的点击事件

                totalTimeIndex++
                if(totalTimeIndex % CHANGE_TIMES == 0) {
                    learnMode = NewLearnMode()
                    hideOptions()
                    updateStatus()
                }


            }

            else -> {}
        }

    }


    fun initOptions() {
        if(!mBinding.viewStubChooseMeaning.isInflated) {
            mBinding.viewStubChooseMeaning.viewStub?.inflate()
        }

        viewStubBinding = DataBindingUtil.inflate(LayoutInflater.from(this),
            R.layout.view_stub_choose_meaning, mBinding.container, false) // binding.container is the ViewGroup

//        mBinding.viewStubChooseMeaning.viewStub?.visibility = View.VISIBLE

//        mBinding.viewStubChooseMeaning.setOnInflateListener(object : OnInflateListener {
//            override fun onInflate(stub: ViewStub?, inflated: View?) {
//                 viewStubBinding = inflated?.let {
//                    DataBindingUtil.bind(
//                        it
//                    )
//                }
//            }
//
//        })
    }


    fun showOptions() {
        mBinding.viewStubChooseMeaning.viewStub?.visibility = View.VISIBLE
    }

    fun hideOptions() {
        mBinding.viewStubChooseMeaning.viewStub?.visibility = View.GONE
    }


    override fun onBackPressed() {
        MainServiceProvider.toMain(this@ReciteWordActivity)
        // TODO 释放音频

        finish()
    }

}



sealed class LearnMode
class NewLearnMode : LearnMode()
class ReviseMode : LearnMode()