package com.gp.recite.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.gp.common.model.FinishWordBean
import com.gp.common.model.LearnProcess
import com.gp.common.model.WordLearnList
import com.gp.framework.toast.TipsToast
import com.gp.network.manager.WordBookIdManager
import com.gp.network.viewmodel.BaseViewModel
import com.gp.recite.repository.ReciteRepo


class ReciteWordsViewModel : BaseViewModel() {

    private val reciteRepo by lazy { ReciteRepo() }

    fun getWordLearn(size : Int) : LiveData<WordLearnList> {
        return liveData {
            val response = safeApiCall(errorBlock = {code, errorMsg ->
                TipsToast.showTips(errorMsg)
            }) {
                reciteRepo.getLearnWord(size)
            }
            response?.let {
                emit(it)
            }
        }
    }

    fun finishLearn(wordIdList : List<Int>, success: (Void?) -> Unit) : LiveData<Void> {
        return liveData{
            val response = launchUIWithResult({
                val finishWordBean = FinishWordBean(
                    WordBookIdManager.getWordBookId(),
                    wordIdList
                )
                reciteRepo.finishWordLearn(finishWordBean)
            }, errorCall = object : com.gp.network.callback.IApiErrorCallback {

            }) {
                success.invoke(it)
            }
        }
    }

    fun getProcess(bookId : Int) : LiveData<LearnProcess> {
        return liveData {
            val response = safeApiCall(errorBlock = {code, errorMsg ->
                TipsToast.showTips(errorMsg)
            }) {
                reciteRepo.getLearnProcess(bookId)
            }
            response?.let {
                emit(it)
            }
        }
    }


}