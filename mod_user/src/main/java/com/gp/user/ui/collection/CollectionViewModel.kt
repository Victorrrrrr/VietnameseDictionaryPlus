package com.gp.user.ui.collection

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.gp.common.model.FolderWordList
import com.gp.framework.toast.TipsToast
import com.gp.network.callback.IApiErrorCallback
import com.gp.network.viewmodel.BaseViewModel
import com.gp.user.repository.UserRepo

class CollectionViewModel : BaseViewModel() {

    private val userRepo by lazy { UserRepo() }

    fun getFolderWordList(folderId: Int,
                          currentPage: Int,
                          pageSize: Int,
                          keyword:String) : LiveData<FolderWordList> {
        return liveData {
            val response = safeApiCall(errorBlock = {code, errorMsg ->
                TipsToast.showTips(errorMsg)
            }) {
                userRepo.getFolderWords(folderId, currentPage, pageSize, keyword)
            }
            response?.let{
                emit(it)
            }
        }
    }

    fun deleteWordInFolder(folderId : Int, wordId : Int, success: (Void?) -> Unit) : LiveData<Void> {
        return liveData {
            val response = launchUIWithResult({
                userRepo.deleteWordsInFolder(folderId, wordId)
            }, errorCall = object : IApiErrorCallback {

            }) {
                success.invoke(it)
            }
        }
    }



}