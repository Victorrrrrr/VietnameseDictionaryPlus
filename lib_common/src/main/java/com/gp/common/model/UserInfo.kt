package com.gp.common.model

data class UserInfo(
    val account: String = "",
    val email: String = "",
    val ext: Ext? = null,
    val password: String = "",
    val username: String = ""
)

data class Ext(
    val avatar: String,
    val gender: String
)