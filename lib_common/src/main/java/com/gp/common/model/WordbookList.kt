package com.gp.common.model

data class WordbookList(
    val currentPage: Int,
    val `data`: List<WordBook>,
    val pageSize: Int,
    val pages: Int,
    val total: Int
)

data class WordBook(
    val count: Int,
    val desc: String?,
    val id: Int,
    val name: String,
    var isSelected: Boolean = false
)


data class LearnProcess(
    val process: Int
)