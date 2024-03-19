package com.gp.main.ui.daily.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import com.gp.common.model.PersonDaily
import com.gp.common.model.SceneryDaily
import com.gp.framework.toast.TipsToast
import com.gp.main.repository.MainRepository
import com.gp.network.viewmodel.BaseViewModel

class DailyViewModel : BaseViewModel() {

    private val mainRepo by lazy { MainRepository() }


    fun getPersonDailyData() : LiveData<ArrayList<PersonDaily?>> {
        return liveData {
            val response = safeApiCall(errorBlock = {code, errorMsg ->
                TipsToast.showTips(errorMsg)
            }) {
                mainRepo.getPersonDaily()
            }
            response?.let{
                emit(it)
            }
        }
    }


    fun getSceneryDailyData() : LiveData<ArrayList<SceneryDaily?>> {
        return liveData {
            val response = safeApiCall(errorBlock = {code, errorMsg ->
                TipsToast.showTips(errorMsg)
            }) {
                mainRepo.getSceneryDaily()
            }
            response?.let{
                emit(it)
            }
        }
    }







}