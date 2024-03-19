package com.gp.common.model


import com.google.gson.annotations.SerializedName

data class WordRandomBeanItem(
    @SerializedName("frequency")
    val frequency: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("isProofread")
    val isProofread: String,
    @SerializedName("lexicon")
    val lexicon: String,
    @SerializedName("pos")
    val pos: String,
    @SerializedName("source")
    val source: String,
    @SerializedName("wordEn")
    val wordEn: String,
    @SerializedName("wordVi")
    val wordVi: String,
    @SerializedName("wordZh")
    val wordZh: String
)