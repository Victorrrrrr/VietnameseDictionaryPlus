package com.gp.user.ui.wordbook

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.gp.common.model.SearchWordBean
import com.gp.common.model.WordbookList
import com.gp.framework.toast.TipsToast
import com.gp.network.callback.IApiErrorCallback
import com.gp.network.viewmodel.BaseViewModel
import com.gp.user.repository.UserRepo

class WordBookViewModel : BaseViewModel() {

    private val userRepo by lazy { UserRepo() }


    fun getWordbookList(currentPage: Int, pageSize: Int, keyword: String): LiveData<WordbookList?> {
        return liveData {
            val response = safeApiCall(errorBlock = { code, errorMsg ->
                TipsToast.showTips(errorMsg)
            }) {
                userRepo.getWordbookList(currentPage, pageSize, keyword)
            }
            response?.let {
                emit(it)
            }
        }
    }


    fun postWordbookId(id: Int, success: (Void?) -> Unit): LiveData<Void> {
        return liveData {
            val response = launchUIWithResult({
                userRepo.postWordbookChoose(id)
            }, errorCall = object : IApiErrorCallback {

            }) {
                success.invoke(it)
            }
        }
    }
}