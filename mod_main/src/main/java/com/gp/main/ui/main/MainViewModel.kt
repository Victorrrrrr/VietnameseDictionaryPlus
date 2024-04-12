package com.gp.main.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.gp.common.model.AuthClientBean

import com.gp.common.model.AuthPasswordBean
import com.gp.common.model.BaiduAuthClientBean
import com.gp.common.model.DailyHomeBean
import com.gp.common.model.FolderAddRequest
import com.gp.common.model.FolderList
import com.gp.common.model.WordRandomBean
import com.gp.framework.toast.TipsToast
import com.gp.main.repository.MainRepository
import com.gp.network.callback.IApiErrorCallback
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


    fun sendBaiduAuthRequestClient(clientId : String, clientSecret : String) : LiveData<BaiduAuthClientBean> {
        return liveData {
            val response = safeApiCall(errorBlock = {code, errorMsg ->
                TipsToast.showTips(errorMsg)
            }) {
                mainRepo.baiduClient(clientId, clientSecret)
            }
            response?.let {
                emit(it)
            }
        }
    }


    fun addFolder(body: FolderAddRequest, success: (Void?) -> Unit) : LiveData<Void> {
        return liveData {
            val response = launchUIWithResult({
                mainRepo.addFolder(body)
            }, errorCall = object : IApiErrorCallback {

            }) {
                success.invoke(it)
            }
        }
    }


    fun getFolderList(): LiveData<FolderList> {
        return liveData {
            val response = safeApiCall(errorBlock = {code, errorMsg ->
                TipsToast.showTips(errorMsg)
            }) {
                mainRepo.getFolderList(1,100)
            }
            response?.let{
                emit(it)
            }
        }
    }




}