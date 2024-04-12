package com.gp.recite.repository

import com.gp.common.model.FinishWordBean
import com.gp.common.model.LearnProcess
import com.gp.common.model.WordLearnList
import com.gp.network.manager.ApiManager
import com.gp.network.repository.BaseRepository
import com.gp.network.response.BaseResponse

class ReciteRepo : BaseRepository() {

    suspend fun getLearnWord(size : Int) : WordLearnList? {
        return requestResponse {
            ApiManager.api.getLearnWord(size)
        }
    }


    suspend fun finishWordLearn(body : FinishWordBean) : BaseResponse<Void>? {
        return requestBaseDataResponse {
            ApiManager.api.finishLearn(body)
        }
    }

    suspend fun getLearnProcess(bookId : Int) : LearnProcess? {
        return requestResponse {
            ApiManager.api.getLearnProcess(bookId)
        }
    }


}