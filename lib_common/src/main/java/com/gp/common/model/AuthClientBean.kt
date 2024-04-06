package com.gp.common.model

data class AuthClientBean(
    val access_token: String,
    val expires_in: Int,
    val jti: String,
    val scope: String,
    val token_type: String
)