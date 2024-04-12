package com.gp.common.model

data class SuggestList(
    val currentPage: Int,
    val `data`: List<SuggestListItem>,
    val pageSize: Int,
    val pages: Int,
    val total: Int
)