package com.gp.learn.game

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.gp.common.model.WordLearnList
import com.gp.framework.toast.TipsToast
import com.gp.learn.repository.LearnRepository
import com.gp.network.callback.IApiErrorCallback
import com.gp.network.manager.WordBookIdManager
import com.gp.network.viewmodel.BaseViewModel

class GameViewModel : BaseViewModel() {

    private val learnRepo by lazy { LearnRepository() }

    fun getWordLearn(size: Int, success: (WordLearnList?) -> Unit): LiveData<WordLearnList> {
        return liveData {
            val response = launchUIWithResult({
                learnRepo.getLearnWord(size)
            }, errorCall = object : IApiErrorCallback {

            }) {
                success.invoke(it)
            }
        }
    }


}