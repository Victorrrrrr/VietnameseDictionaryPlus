package com.gp.main.repository

import com.gp.common.model.ArticleList
import com.gp.common.model.AuthBean
import com.gp.network.manager.ApiManager
import com.gp.network.repository.BaseRepository

class HomeRepository : BaseRepository() {


    suspend fun getHomeInfoList(page: Int): ArticleList? {
        return requestResponse {
            ApiManager.api.getHomeList(page,20)
        }
    }


    suspend fun authToken(grantType : String, clientId : String, clientSecret : String) : AuthBean? {
        return requestAuthResponse {
            ApiManager.api.appAuthToken(grantType, clientId, clientSecret)
        }
    }

}