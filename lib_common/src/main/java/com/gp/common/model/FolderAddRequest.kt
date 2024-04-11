package com.gp.common.model

data class FolderAddRequest(
    val desc: String,
    val name: String,
)


data class FolderListBean(
    val desc: String = "",
    val id: Int = 0,
    val name: String = "",
    val count: Int = 0,
    var isSelected : Boolean = false
)

