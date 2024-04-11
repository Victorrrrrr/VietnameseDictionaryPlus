package com.gp.search.repo

import com.gp.common.model.AddWordToFolderRequest
import com.gp.common.model.FolderList
import com.gp.common.model.SearchWordBean
import com.gp.common.model.WordBeanItem
import com.gp.network.manager.ApiManager
import com.gp.network.repository.BaseRepository
import com.gp.network.response.BaseResponse
import com.gp.room.manager.WordManager

class SearchRepository : BaseRepository() {

    suspend fun getWordList(currentPage: Int, pageSize: Int, keyword: String): SearchWordBean? {
        return requestResponse {
                ApiManager.api.getWordList(currentPage, pageSize, keyword)
        }
    }


    private var isLocalDataGet = false

    suspend fun getWordDetail(id: String): WordBeanItem? {
        return requestResponse {
            ApiManager.api.getWordDetail(id)
        }
    }


    suspend fun getFolderList(currentPage: Int, pageSize: Int) : FolderList? {
        return requestResponse {
            ApiManager.api.getFolderList(currentPage, pageSize)
        }
    }

    suspend fun addWordToFolder(addWordToFolderRequest: AddWordToFolderRequest) : BaseResponse<Void>? {
        return requestBaseDataResponse {
            ApiManager.api.addWordToFolder(addWordToFolderRequest)
        }
    }

}