package com.gp.mod_demo.ui.main

import com.gp.common.model.Article
import com.gp.framework.base.IUiState

data class MainState(val detailUiState: DetailUiState) : IUiState


//sealed class BannerUiState {
//    object INIT : BannerUiState()
//    data class SUCCESS(val models: List<Banner>) : BannerUiState()
//}

sealed class DetailUiState {
    object INIT : DetailUiState()
    data class SUCCESS(val articles: Article) : DetailUiState()
}