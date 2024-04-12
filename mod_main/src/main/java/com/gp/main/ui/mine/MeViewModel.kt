package com.gp.main.ui.mine

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.gp.common.model.DailyHomeBean
import com.gp.common.model.LearnProcess
import com.gp.framework.toast.TipsToast
import com.gp.main.repository.MainRepository
import com.gp.network.viewmodel.BaseViewModel

class MeViewModel : BaseViewModel() {

    private val mainRepo by lazy { MainRepository() }

    fun getProcess(bookId : Int) : LiveData<LearnProcess> {
        return liveData {
            val response = safeApiCall(errorBlock = {code, errorMsg ->
                TipsToast.showTips(errorMsg)
            }) {
                mainRepo.getLearnProcess(bookId)
            }
            response?.let {
                emit(it)
            }
        }
    }



}