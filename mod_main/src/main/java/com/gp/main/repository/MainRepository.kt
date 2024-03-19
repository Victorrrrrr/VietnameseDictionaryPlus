package com.gp.main.repository

import com.gp.common.model.AuthClientBean
import com.gp.common.model.AuthPasswordBean
import com.gp.common.model.DailyHomeBean
import com.gp.common.model.UserInfo
import com.gp.common.model.WordRandomBean
import com.gp.network.manager.ApiManager
import com.gp.network.repository.BaseRepository
import com.gp.network.response.BaseResponse

class MainRepository : BaseRepository() {

    suspend fun authClient(grantType : String, clientId : String, clientSecret : String) : AuthClientBean? {
        return requestAuthResponse {
            ApiManager.api.appAuthClient(grantType, clientId, clientSecret)
        }
    }



    suspend fun getHomeDailyData() : DailyHomeBean? {
        return requestResponse {
            ApiManager.api.getDailyHomeData()
        }
    }


    suspend fun getWordRandom() : WordRandomBean? {
        return requestResponse {
            ApiManager.api.getWordRandom()
        }
    }




}