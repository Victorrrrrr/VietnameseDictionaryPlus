package com.gp.common.model


import com.google.gson.annotations.SerializedName

data class WordDetail(
    @SerializedName("code")
    val code: Int,
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("msg")
    val msg: String
)