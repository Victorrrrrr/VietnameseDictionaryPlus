package com.gp.common.model

data class SearchWordBean(
    val currentPage: Int,
    val data: List<SingleWordBean>,
    val pageSize: Int,
    val pages: Int,
    val total: Int
)