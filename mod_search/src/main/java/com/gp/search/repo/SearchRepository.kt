package com.gp.search.repo

import com.gp.common.model.AddWordToFolderRequest
import com.gp.common.model.FolderList
import com.gp.common.model.FolderWordList
import com.gp.common.model.SearchWordBean
import com.gp.common.model.SuggestList
import com.gp.common.model.SuggestRequest
import com.gp.common.model.WordBeanItem
import com.gp.network.manager.ApiManager
import com.gp.network.repository.BaseRepository
import com.gp.network.response.BaseResponse

class SearchRepository : BaseRepository() {

    suspend fun getWordList(currentPage: Int, pageSize: Int, keyword: String): SearchWordBean? {
        return requestResponse {
            ApiManager.api.getWordList(currentPage, pageSize, keyword)
        }
    }


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


    suspend fun getFolderWords(folderId: Int,
                               currentPage: Int,
                               pageSize: Int,
                               keyword:String) : FolderWordList? {
        return requestResponse {
            ApiManager.api.getFolderWords(folderId, currentPage, pageSize, keyword)
        }
    }

    suspend fun deleteWordsInFolder(folderId: Int, wordId: Int) : BaseResponse<Void>? {
        return requestBaseDataResponse {
            ApiManager.api.deleteWordInFolder(folderId, wordId)
        }
    }

    suspend fun suggest(suggestListItem: SuggestRequest) : BaseResponse<Void>? {
        return requestBaseDataResponse {
            ApiManager.api.suggest(suggestListItem)
        }
    }


    suspend fun getSuggestList(id: Int, currentPage: Int, pageSize: Int): SuggestList? {
        return requestResponse {
            ApiManager.api.getSuggestList(id, currentPage, pageSize)
        }
    }



}