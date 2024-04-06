package com.gp.common.model

data class WordBeanItem(
    val frequency: Int,
    val id: Int,
    val isProofread: String,
    val lexicon: String,
    val pos: String,
    val pronounceEn: String,
    val pronounceVi: String,
    val pronounceZh: String,
    val source: String,
    val wordEn: String,
    val wordVi: String,
    val wordZh: String
)