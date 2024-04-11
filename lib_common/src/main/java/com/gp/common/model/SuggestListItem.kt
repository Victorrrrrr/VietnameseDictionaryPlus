package com.gp.common.model

data class SuggestListItem(
    val content: String,
    val dataId: Int,
    val id: Int,
    val status: Int,
    val type: Int,
    val userId: Int
)