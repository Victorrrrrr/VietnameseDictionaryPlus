package com.gp.common.model

data class BaiduTranslateResponse(
    val log_id: Long,
    val voiceTranslateResult: VoiceTranslateResult,
    val error_msg : String,
    val error_code : Long
)

data class VoiceTranslateResult(
    val source: String,
    val target: String,
    val target_tts: String
)