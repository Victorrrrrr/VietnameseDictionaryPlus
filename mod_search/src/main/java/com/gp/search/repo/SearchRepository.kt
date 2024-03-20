package com.gp.search.repo

import com.gp.common.model.WordBeanItem
import com.gp.network.manager.ApiManager
import com.gp.network.repository.BaseRepository

class SearchRepository : BaseRepository(){

    suspend fun getWordDetail(id: String) : WordBeanItem? {
        return requestResponse {
            ApiManager.api.getWordDetail(id)
        }

    }




}