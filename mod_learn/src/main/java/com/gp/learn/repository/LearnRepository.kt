package com.gp.learn.repository

import com.gp.common.model.BaiduTranslateResponse
import com.gp.common.model.BaiduVoiceRequest
import com.gp.common.model.WordLearnList
import com.gp.network.manager.ApiManager
import com.gp.network.manager.TokenManager
import com.gp.network.repository.BaseRepository
import com.gp.network.response.BaseResponse

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

    suspend fun getLearnWord(size : Int) : BaseResponse<WordLearnList>? {
        return requestBaseDataResponse {
            ApiManager.api.getLearnWord(size)
        }
    }


}