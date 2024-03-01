package com.gp.mod_demo.ui.main

import com.gp.framework.base.BaseViewModel
import com.gp.framework.base.IUiIntent
import com.gp.mod_demo.model.repo.HomeRepository

class MainViewModel() : BaseViewModel<MainState, MainIntent>() {
    val homeRepository by lazy { HomeRepository() }

    override fun initUiState(): MainState {
        return MainState(DetailUiState.INIT)
    }

    override fun handleIntent(intent: IUiIntent) {
        when (intent) {
            is MainIntent.GetDetail -> {
                requestDataWithFlow(showLoading = true,
                    request = { homeRepository.getHomeInfoList(intent.page) },
                    successCallback = { data ->
                        sendUiState {
                            copy(
                                detailUiState = DetailUiState.SUCCESS(
                                    data
                                )
                            )
                        }
                    })
            }
        }
    }


}