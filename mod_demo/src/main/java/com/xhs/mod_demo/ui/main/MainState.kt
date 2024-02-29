package com.xhs.mod_demo.ui.main

import com.gp.framework.base.IUiState
import com.xhs.mod_demo.model.bean.Article
import com.xhs.mod_demo.model.bean.Banner

data class MainState(val bannerUiState: BannerUiState) : IUiState


sealed class BannerUiState {
    object INIT : BannerUiState()
    data class SUCCESS(val models: List<Banner>) : BannerUiState()
}

sealed class DetailUiState {
    object INIT : DetailUiState()
    data class SUCCESS(val articles: Article) : DetailUiState()
}