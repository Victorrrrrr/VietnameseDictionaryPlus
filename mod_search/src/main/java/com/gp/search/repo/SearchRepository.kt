package com.gp.search.repo

import com.gp.common.model.WordDetail
import com.gp.network.manager.ApiManager
import com.gp.network.repository.BaseRepository

class SearchRepository : BaseRepository(){

    suspend fun getWordDetail(id: String) : WordDetail? {
        return requestResponse {
            ApiManager.api.getWordDetail(id)
        }
    }




}