package com.gp.recite.repository

import com.gp.common.model.FinishWordBean
import com.gp.common.model.WordLearnList
import com.gp.network.manager.ApiManager
import com.gp.network.repository.BaseRepository

class ReciteRepo : BaseRepository() {

    suspend fun getLearnWord(size : Int) : WordLearnList? {
        return requestResponse {
            ApiManager.api.getLearnWord(size)
        }
    }


    suspend fun finishWordLearn(body : FinishWordBean) : Void? {
        return requestResponse {
            ApiManager.api.finishLearn(body)
        }
    }

}