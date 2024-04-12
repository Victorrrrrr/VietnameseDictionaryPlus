package com.gp.learn.tool.voice_trans

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.gp.common.model.AuthClientBean
import com.gp.common.model.BaiduTranslateResponse
import com.gp.framework.toast.TipsToast
import com.gp.learn.repository.LearnRepository
import com.gp.network.viewmodel.BaseViewModel

class VoiceTransViewModel : BaseViewModel() {

    private val learnRepo by lazy { LearnRepository() }

    fun sendVoiceTranslate(
        fromLanguage: String,
        toLanguage: String,
        voice: String,
        format: String
    ) : LiveData<BaiduTranslateResponse?> {
        return liveData {
            val response = safeApiCall(errorBlock = {code, errorMsg ->
                TipsToast.showTips(errorMsg)
            }) {
                learnRepo.sendVoiceTranslate(fromLanguage, toLanguage, voice, format)
            }
            response?.let {
                emit(it)
            }
        }
    }



}