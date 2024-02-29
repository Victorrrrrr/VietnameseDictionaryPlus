package com.xhs.mod_demo.ui.main

import com.gp.framework.base.IUiIntent

sealed class MainIntent : IUiIntent {
    object GetBanner : MainIntent()
    data class GetDetail(val page: Int) : MainIntent()
}