package com.gp.common.model

data class AddWordToFolderRequest(
    val folderId: Int,
    val wordInfo: List<WordInfo>
)