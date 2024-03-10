package com.gp.login.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.gp.common.model.AuthPasswordBean
import com.gp.common.model.UserInfo
import com.gp.framework.toast.TipsToast
import com.gp.network.viewmodel.BaseViewModel

class LoginViewModel : BaseViewModel() {

    private val loginRepo by lazy { LoginRepository() }

    fun sendAuthRequestPassword(username : String,
                                pwd : String,
                                grantType : String = "password") : LiveData<AuthPasswordBean> {
        return liveData {
            val response = safeApiCall(errorBlock = {code, errorMsg ->
                TipsToast.showTips(errorMsg)
            }) {
                loginRepo.authPassword(grantType, username, pwd)
            }
            response?.let {
                emit(it)
            }
        }
    }


    fun sendRegisterRequest(username: String,
                            pwd: String,
                            email: String) : LiveData<Void> {
        return liveData {
            val response = safeApiCall(errorBlock = {code, errorMsg ->
                TipsToast.showTips(errorMsg)
            }) {
                val userInfo = UserInfo(
                    username = username,
                    password = pwd,
                    email = email
                    )
                loginRepo.register(userInfo)
            }
            response?.let {
                emit(it)
            }
        }

    }

}