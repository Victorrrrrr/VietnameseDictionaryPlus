package com.gp.main.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.gp.common.model.AuthClientBean

import com.gp.common.model.AuthPasswordBean
import com.gp.common.model.DailyHomeBean
import com.gp.common.model.WordRandomBean
import com.gp.framework.toast.TipsToast
import com.gp.main.repository.MainRepository
import com.gp.network.viewmodel.BaseViewModel

class MainViewModel : BaseViewModel() {

    private val mainRepo by lazy { MainRepository() }

    fun getHomeDailyData() : LiveData<DailyHomeBean> {
        return liveData {
            val response = safeApiCall(errorBlock = {code, errorMsg ->
                TipsToast.showTips(errorMsg)
            }) {
                mainRepo.getHomeDailyData()
            }
            response?.let {
                emit(it)
            }
        }
    }


    fun getWordRandom() : LiveData<WordRandomBean> {
        return liveData {
            val response = safeApiCall(errorBlock = {code, errorMsg ->
                TipsToast.showTips(errorMsg)
            }) {
                mainRepo.getWordRandom()
            }
            response?.let {
                emit(it)
            }
        }
    }


    fun sendAuthRequestClient(grantType : String = "client_credentials",
                        clientId : String,
                        clientSecret : String) : LiveData<AuthClientBean> {
        return liveData {
            val response = safeApiCall(errorBlock = {code, errorMsg ->
                TipsToast.showTips(errorMsg)
            }) {
                mainRepo.authClient(grantType, clientId, clientSecret)
            }
            response?.let {
                emit(it)
            }
        }
    }


}