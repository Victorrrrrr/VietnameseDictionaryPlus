package com.gp.learn.tool.note

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.gp.common.model.FolderList
import com.gp.common.model.FolderAddRequest
import com.gp.common.model.FolderWordList
import com.gp.framework.toast.TipsToast
import com.gp.learn.repository.LearnRepository
import com.gp.network.callback.IApiErrorCallback
import com.gp.network.viewmodel.BaseViewModel

class FolderViewModel : BaseViewModel() {

    private val learnRepo by lazy { LearnRepository() }

    fun getFolderList(): LiveData<FolderList> {
        return liveData {
            val response = safeApiCall(errorBlock = {code, errorMsg ->
                TipsToast.showTips(errorMsg)
            }) {
                learnRepo.getFolderList(1,100)
            }
            response?.let{
                emit(it)
            }
        }
    }

    fun addFolder(body: FolderAddRequest, success: (Void?) -> Unit) : LiveData<Void> {
        return liveData {
            val response = launchUIWithResult({
                learnRepo.addFolder(body)
            }, errorCall = object : IApiErrorCallback {

            }) {
                success.invoke(it)
            }
        }
    }


    fun deleteFolderById(folderId : Int, success: (Void?) -> Unit) : LiveData<Void>{
        return liveData {
            val response = launchUIWithResult({
                learnRepo.deleteFolder(folderId)
            }, errorCall = object : IApiErrorCallback {

            }) {
                success.invoke(it)
            }
        }
    }


    fun getFolderWordList(folderId: Int,
                          currentPage: Int,
                          pageSize: Int,
                          keyword:String) : LiveData<FolderWordList> {
        return liveData {
            val response = safeApiCall(errorBlock = {code, errorMsg ->
                TipsToast.showTips(errorMsg)
            }) {
                learnRepo.getFolderWords(folderId, currentPage, pageSize, keyword)
            }
            response?.let{
                emit(it)
            }
        }
    }


    fun deleteWordInFolder(folderId : Int, wordId : Int, success: (Void?) -> Unit) : LiveData<Void> {
        return liveData {
            val response = launchUIWithResult({
                learnRepo.deleteWordsInFolder(folderId, wordId)
            }, errorCall = object : IApiErrorCallback {

            }) {
                success.invoke(it)
            }
        }
    }



}