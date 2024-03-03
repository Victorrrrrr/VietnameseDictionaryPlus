package com.gp.mod_demo.ui.main

import com.gp.framework.base.IUiIntent

sealed class MainIntent : IUiIntent {
    data class GetDetail(val page: Int) : MainIntent()
}