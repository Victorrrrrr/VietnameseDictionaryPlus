package com.gp.framework.base

import androidx.annotation.Keep


@Keep
interface IUiState

@Keep
interface IUiIntent

sealed class LoadUiIntent {
    data class Loading(var isShow: Boolean) : LoadUiIntent()
    object ShowMainView : LoadUiIntent()
    data class Error(val msg: String) : LoadUiIntent()
}