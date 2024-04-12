package com.gp.common.model

data class SuggestListItem(
    val content: String,
    val dataId: Int,
    val id: Int,
    val status: Int,
    val type: Int,
    val userId: Int,
    val username: String
)


data class SuggestRequest(
    val content: String,
    val dataId: Int = 0,
    val type: Int
)