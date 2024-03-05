package com.gp.main.ui.home

import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.RotateAnimation
import com.gp.common.provider.SearchServiceProvider
import com.gp.framework.base.BaseMvvmFragment
import com.gp.framework.ext.onClick
import com.gp.main.databinding.FragmentHomeBinding


class HomeFragment : BaseMvvmFragment<FragmentHomeBinding, HomeViewModel>() {

    companion object {
        const val GRANT_TYPE = "password"
        const val USERNAME = "wzf"
        const val PASSWORD = "123456"
    }


    override fun initView(view: View, savedInstanceState: Bundle?) {
//        mViewModel.getHomeInfoList(0).observe(this) {
//            Log.d("1112", "result : ${it}")
//        }

//        mViewModel.sendAuthRequest(
//            GRANT_TYPE,
//            USERNAME,
//            PASSWORD
//        ).observe(this) {
//            Log.d("1112", "result : ${it}")
//        }

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

    }

}