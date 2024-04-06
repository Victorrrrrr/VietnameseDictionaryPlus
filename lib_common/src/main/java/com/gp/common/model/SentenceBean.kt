package com.gp.common.model

data class SentenceBean (
    var id : Int = 0,
    var sentenceV : String,
    var sentenceC : String,
    var voiceUrl : String? = "",
    var isCollect : Boolean = false
)