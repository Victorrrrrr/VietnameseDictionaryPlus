package com.gp.main.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData

import com.gp.common.model.ArticleList
import com.gp.common.model.AuthBean
import com.gp.framework.toast.TipsToast
import com.gp.main.repository.HomeRepository
import com.gp.network.viewmodel.BaseViewModel

class MainViewModel : BaseViewModel() {

    private val homeRepo by lazy { HomeRepository() }

    fun getHomeInfoList(page:Int): LiveData<ArticleList> {
        return liveData{
            val response = safeApiCall(errorBlock = {code, errorMsg ->
                TipsToast.showTips(errorMsg)
            }) {
                homeRepo.getHomeInfoList(page)
            }
            response?.let {
                emit(it)
            }
        }
    }


    fun sendAuthRequest(grantType : String,
                        clientId : String,
                        clientSecret : String) : LiveData<AuthBean> {
        return liveData {
            val response = safeApiCall(errorBlock = {code, errorMsg ->
                TipsToast.showTips(errorMsg)
            }) {
                homeRepo.authToken(grantType, clientId, clientSecret)
            }
            response?.let {
                emit(it)
            }
        }
    }

}