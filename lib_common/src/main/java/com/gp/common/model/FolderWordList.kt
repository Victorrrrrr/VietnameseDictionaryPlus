package com.gp.common.model

data class FolderWordList(
    val currentPage: Int,
    val `data`: List<WordBeanItem>,
    val pageSize: Int,
    val pages: Int,
    val total: Int
)
