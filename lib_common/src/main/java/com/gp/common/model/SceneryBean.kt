package com.gp.common.model

data class SceneryBean(
    val daily: SceneryDaily,
    val recommendList: List<SceneryDaily>
)