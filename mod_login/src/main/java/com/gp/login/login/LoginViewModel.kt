package com.gp.login.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import com.gp.common.model.AuthPasswordBean
import com.gp.common.model.UserInfo
import com.gp.framework.toast.TipsToast
import com.gp.network.viewmodel.BaseViewModel

class LoginViewModel : BaseViewModel() {

    private val loginRepo by lazy { LoginRepository() }
    val loginLiveData = MutableLiveData<AuthPasswordBean?>()
    val registerLiveData = MutableLiveData<Void?>()

    fun sendAuthRequestPassword(username : String,
                                pwd : String,
                                grantType : String = "password") : LiveData<AuthPasswordBean?> {
        launchUI(errorBlock = {code, error ->
            TipsToast.showTips(error)
            loginLiveData.value = null
        }) {
            val data = loginRepo.authPassword(grantType, username, pwd)
            loginLiveData. value = data
        }
        return loginLiveData
    }


    fun sendRegisterRequest(username: String,
                            pwd: String,
                            email: String) : LiveData<Void?> {
        launchUI(errorBlock = {code, error ->
            TipsToast.showTips(error)
        }) {
            val userInfo = UserInfo(
                account = username,
                username = username,
                password = pwd,
                email = email
            )
            registerLiveData.value = loginRepo.register(userInfo)
        }
        return registerLiveData
    }

}