package com.gp.common.model
import com.google.gson.annotations.SerializedName


data class AuthBean(
    @SerializedName("access_token")
    val accessToken: String,
    @SerializedName("create_time")
    val createTime: String,
    @SerializedName("expires_in")
    val expiresIn: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("jti")
    val jti: String,
    @SerializedName("permission_list")
    val permissionList: String,
    @SerializedName("role_list")
    val roleList: String,
    @SerializedName("scope")
    val scope: String,
    @SerializedName("token_type")
    val tokenType: String
)