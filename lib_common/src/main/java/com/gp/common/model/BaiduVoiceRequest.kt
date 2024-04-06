package com.gp.common.model

data class BaiduVoiceRequest(
    val format: String,
    val from: String,
    val to: String,
    val voice: String
)