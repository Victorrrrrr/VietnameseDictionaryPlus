package com.gp.network.api

import com.gp.common.model.BaiduAuthClientBean
import com.gp.common.model.BaiduTranslateResponse
import com.gp.common.model.BaiduVoiceRequest
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface BaiduService {

    /**
     * grant_type： 必须参数，固定为client_credentials；
     * client_id： 必须参数，应用的API Key；
     * client_secret： 必须参数，应用的Secret Key；
     */
    @POST("oauth/2.0/token")
    suspend fun oauthToken(
        @Query("grant_type") grantType : String,
        @Query("client_id") clientId : String,
        @Query("client_secret") clientSecret : String
    ) : BaiduAuthClientBean


    @POST("rpc/2.0/mt/v2/speech-translation")
    suspend fun voiceTranslate(
        @Query("access_token") token : String,
        @Body baiduVoiceRequest: BaiduVoiceRequest
    ) : BaiduTranslateResponse
}