package com.gp.common.model

data class MusicBean(
    val daily: MusicDaily,
    val recommendList: List<MusicDaily>
)