package com.gp.common.model

data class PersonBean(
    val daily: PersonDaily,
    val recommendList: List<PersonDaily>
)