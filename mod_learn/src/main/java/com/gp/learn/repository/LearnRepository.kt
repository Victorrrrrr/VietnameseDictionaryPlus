package com.gp.learn.repository

import com.gp.common.model.BaiduTranslateResponse
import com.gp.common.model.BaiduVoiceRequest
import com.gp.network.manager.ApiManager
import com.gp.network.manager.TokenManager
import com.gp.network.repository.BaseRepository

class LearnRepository : BaseRepository(){

    suspend fun sendVoiceTranslate(
        fromLanguage: String,
        toLanguage: String,
        voice: String,
        format: String
    ): BaiduTranslateResponse? {
        return requestAuthResponse {
            val request = BaiduVoiceRequest(fromLanguage, toLanguage, voice, format)
            val token = TokenManager.getBaiduToken()
            ApiManager.baidu.voiceTranslate(token?:"", request)
        }
    }

}