package com.gp.mod_demo.ui.main.fragment


import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope

import com.gp.framework.base.BaseBindingFragment
import com.gp.framework.utils.LogUtil
import com.gp.mod_demo.R
import com.gp.mod_demo.databinding.FragmentHomeBinding
import com.gp.mod_demo.ui.main.DetailUiState
import com.gp.mod_demo.ui.main.MainIntent
import com.gp.mod_demo.ui.main.MainViewModel
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map


class HomeFragment : BaseBindingFragment<FragmentHomeBinding, MainViewModel>({
    FragmentHomeBinding.inflate(it)
}) {
    override fun getLayoutResId(): Int = R.layout.fragment_home

    override fun initView(view: View, savedInstanceState: Bundle?) {
        mViewModel.sendUiIntent(MainIntent.GetDetail(0))
    }

    override fun initData() {
        super.initData()

        lifecycleScope.launchWhenStarted {
            mViewModel.uiStateFlow.map { it.detailUiState }
                .distinctUntilChanged()
                .collect { detailUiState ->
                    LogUtil.d(tag = TAG, message = "detailUiState: $detailUiState" )
                    when (detailUiState) {
                        is DetailUiState.INIT -> {}
                        is DetailUiState.SUCCESS -> {
                            LogUtil.d(tag = TAG, message = "detail Success: ${detailUiState.articles.datas}" )
                        }
                    }
                }
        }
    }

}