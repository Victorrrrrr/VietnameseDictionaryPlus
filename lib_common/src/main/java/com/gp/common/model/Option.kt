package com.gp.common.model

data class Option(
    val id: Int,
    val isRight: Boolean,
    val pos: String,
    val pronounceEn: String,
    val pronounceVi: String,
    val pronounceZh: String,
    val wordEn: String,
    val wordVi: String,
    val wordZh: String
)