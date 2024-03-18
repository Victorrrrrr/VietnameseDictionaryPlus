package com.gp.main.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.RotateAnimation
import com.gp.common.provider.ReciteServiceProvider
import com.gp.common.provider.SearchServiceProvider
import com.gp.framework.base.BaseMvvmFragment
import com.gp.framework.ext.onClick
import com.gp.framework.toast.TipsToast
import com.gp.main.databinding.FragmentHomeBinding
import com.gp.main.ui.daily.music.DailyMusicActivity
import com.gp.main.ui.daily.person.DailyPersonActivity
import com.gp.main.ui.daily.scenic.DailyScenicActivity
import com.gp.main.ui.main.MainViewModel


class HomeFragment : BaseMvvmFragment<FragmentHomeBinding, MainViewModel>() {



    override fun initView(view: View, savedInstanceState: Bundle?) {

        initEvent()
    }



    override fun initData() {
        mViewModel.getHomeDailyData().observe(this) {
            mBinding?.sayingBean = it.sentence
        }
    }


    private fun initEvent() {
        val animation = RotateAnimation(
            0.0f, 360F,
            Animation.RELATIVE_TO_SELF, 0.5f,
            Animation.RELATIVE_TO_SELF, 0.5f)
        animation.duration = 1000
        animation.repeatCount = Animation.INFINITE
        mBinding?.ivRefreshWord?.onClick {
            mBinding?.ivRefreshWord?.startAnimation(animation)
            animation.repeatCount=0

            // TODO: 请求刷新单词

        }

        mBinding?.etSearch?.onClick {
            SearchServiceProvider.toSearch(it.context)
        }

        mBinding?.btnToRecite?.onClick {
            ReciteServiceProvider.toReciteLoad(it.context)
        }

        mBinding?.ivTakePhoto?.onClick {
            TipsToast.showTips("拍照权限请求")
            // TODO 请求权限

        }

        mBinding?.clRandomWordLayout?.onClick {
            val word = mBinding?.tvVietnameseWord?.text.toString()
            SearchServiceProvider.toWordPage(context = it.context, word = word)
        }


        // 每日风景
        mBinding?.tvDailyScenic?.onClick {
            val intent = Intent(activity, DailyScenicActivity::class.java)
            startActivity(intent)
        }

        mBinding?.ivDailyScenic?.onClick {
            val intent = Intent(activity, DailyScenicActivity::class.java)
            startActivity(intent)
        }


        // 每日人物
        mBinding?.tvDailyPerson?.onClick {
            val intent = Intent(activity, DailyPersonActivity::class.java)
            startActivity(intent)
        }

        mBinding?.ivDailyPerson?.onClick {
            val intent = Intent(activity, DailyPersonActivity::class.java)
            startActivity(intent)
        }


        // 每日歌曲
        mBinding?.tvDailyMusic?.onClick {
            val intent = Intent(activity, DailyMusicActivity::class.java)
            startActivity(intent)
        }

        mBinding?.ivDailyMusic?.onClick {
            val intent = Intent(activity, DailyMusicActivity::class.java)
            startActivity(intent)
        }

    }

}