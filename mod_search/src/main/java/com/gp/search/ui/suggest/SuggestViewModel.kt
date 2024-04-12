package com.gp.search.ui.suggest

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.gp.common.model.SuggestList
import com.gp.common.model.SuggestListItem
import com.gp.common.model.SuggestRequest
import com.gp.framework.toast.TipsToast
import com.gp.network.callback.IApiErrorCallback
import com.gp.network.viewmodel.BaseViewModel
import com.gp.search.repo.SearchRepository

class SuggestViewModel : BaseViewModel() {

    private val searchRepo by lazy { SearchRepository() }

    fun suggest(suggestListItem: SuggestRequest, success : (Void?) -> Unit) : LiveData<Void> {
        return liveData {
            val response = launchUIWithResult({
                searchRepo.suggest(suggestListItem)
            }, errorCall = object : IApiErrorCallback {

            }) {
                success.invoke(it)
            }
        }
    }





}