package com.gp.common.model

data class FolderList(
    val currentPage: Int,
    val `data`: List<FolderListBean>,
    val pageSize: Int,
    val pages: Int,
    val total: Int
)