package com.gp.login.login

import com.gp.common.model.AuthPasswordBean
import com.gp.common.model.UserInfo
import com.gp.network.manager.ApiManager
import com.gp.network.repository.BaseRepository

class LoginRepository: BaseRepository() {

    suspend fun authPassword(grantType : String, username : String, pwd : String) : AuthPasswordBean? {
        return requestAuthResponse {
            ApiManager.api.appAuthPassword(grantType, username, pwd)
        }
    }

    // TODO return type
    suspend fun register(userInfo: UserInfo) : Void? {
        return requestResponse {
            ApiManager.api.appAuthRegister(userInfo)
        }
    }

}