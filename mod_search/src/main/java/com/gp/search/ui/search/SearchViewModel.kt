package com.gp.search.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.gp.common.model.AddWordToFolderRequest
import com.gp.common.model.FolderList
import com.gp.common.model.FolderWordList
import com.gp.common.model.SearchWordBean
import com.gp.common.model.SuggestList
import com.gp.common.model.WordBeanItem
import com.gp.framework.toast.TipsToast
import com.gp.network.callback.IApiErrorCallback
import com.gp.network.viewmodel.BaseViewModel
import com.gp.search.repo.SearchRepository

class SearchViewModel : BaseViewModel() {

    private val searchRepository by lazy { SearchRepository() }

    fun getWordList(currentPage : Int, pageSize : Int, keyword : String) : LiveData<SearchWordBean> {
        return liveData {
            val response = safeApiCall(errorBlock = {code, errorMsg ->
                TipsToast.showTips(errorMsg)
            }) {
                searchRepository.getWordList(currentPage, pageSize, keyword)
            }
            response?.let {
                emit(it)
            }
        }
    }

    fun getWordDetail(id : String) : LiveData<WordBeanItem> {
        return liveData {
            val response = safeApiCall(errorBlock = {code, errorMsg ->
                TipsToast.showTips(errorMsg)
            }) {
                searchRepository.getWordDetail(id)
            }
            response?.let{
                emit(it)
            }
        }
    }


    fun getFolderList(): LiveData<FolderList> {
        return liveData {
            val response = safeApiCall(errorBlock = {code, errorMsg ->
                TipsToast.showTips(errorMsg)
            }) {
                searchRepository.getFolderList(1,100)
            }
            response?.let{
                emit(it)
            }
        }
    }


    fun addWordToFolder(addWordToFolderRequest: AddWordToFolderRequest, success: (Void?) -> Unit) : LiveData<Void> {
        return liveData {
            val response = launchUIWithResult({
                searchRepository.addWordToFolder(addWordToFolderRequest)
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
                searchRepository.getFolderWords(folderId, currentPage, pageSize, keyword)
            }
            response?.let{
                emit(it)
            }
        }
    }

    fun deleteWordInFolder(folderId : Int, wordId : Int, success: (Void?) -> Unit) : LiveData<Void> {
        return liveData {
            val response = launchUIWithResult({
                searchRepository.deleteWordsInFolder(folderId, wordId)
            }, errorCall = object : IApiErrorCallback {

            }) {
                success.invoke(it)
            }
        }
    }


    fun getSuggestList(id: Int, currentPage: Int, pageSize: Int): LiveData<SuggestList> {
        return liveData {
            val response = safeApiCall(errorBlock = {code, errorMsg ->
                TipsToast.showTips(errorMsg)
            }) {
                searchRepository.getSuggestList(id, currentPage, pageSize)
            }
            response?.let{
                emit(it)
            }
        }
    }




}