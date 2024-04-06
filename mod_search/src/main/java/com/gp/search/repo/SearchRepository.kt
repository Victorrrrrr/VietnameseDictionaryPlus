package com.gp.search.repo

import com.gp.common.model.SearchWordBean
import com.gp.common.model.WordBeanItem
import com.gp.network.manager.ApiManager
import com.gp.network.repository.BaseRepository
import com.gp.room.manager.WordManager

class SearchRepository : BaseRepository() {

    suspend fun getWordList(currentPage: Int, pageSize: Int, keyword: String): SearchWordBean? {
        return if (isLocalDataGet)
            // 本地数据库获取
            WordManager.getWordList()
        else
            // 通过网络获取
            requestResponse {
                ApiManager.api.getWordList(currentPage, pageSize, keyword)
            }
    }


    private var isLocalDataGet = false

    suspend fun getWordDetail(id: String): WordBeanItem? {
        return requestResponse {
            ApiManager.api.getWordDetail(id)
        }
    }
}