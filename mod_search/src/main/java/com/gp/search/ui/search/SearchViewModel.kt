package com.gp.search.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.gp.common.model.SearchWordBean
import com.gp.common.model.WordBeanItem
import com.gp.framework.toast.TipsToast
import com.gp.network.viewmodel.BaseViewModel
import com.gp.search.repo.SearchRepository

class SearchViewModel : BaseViewModel() {

    private val searchRepository by lazy { SearchRepository() }


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

}