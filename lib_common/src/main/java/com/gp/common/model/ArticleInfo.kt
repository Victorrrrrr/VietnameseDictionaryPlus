package com.gp.common.model


data class Article(
    var curPage: Int,
    val datas: List<ArticleItem>
)

data class ArticleItem(
    val title: String,
    val link: String,
    val userId: Int,
    val niceDate: String
)


data class ArticleList(
    val curPage: Int? = 0,
    val offset: Int? = 0,
    val size: Int? = 0,
    val total: Int? = 0,
    val pageCount: Int? = 0,
    val datas: MutableList<ArticleInfo>? = mutableListOf()
)

data class ArticleInfo(
    val id: Int,
    val userId: Int,
    val courseId: Int?,
    val originId: Int?,
    var collect: Boolean? = false,
    val title: String?,
    val desc: String?,
    val link: String?,
    val zan: Int?,
    val niceShareDate: String?,
    val niceDate: String?,
    val publishTime: Long,
    val shareUser: String?,
    val author: String? = "",
    val superChapterName: String? = "",
    val chapterName: String?,
    val tags: MutableList<Any>? = arrayListOf(),
)