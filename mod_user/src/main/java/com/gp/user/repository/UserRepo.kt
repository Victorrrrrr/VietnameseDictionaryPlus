package com.gp.user.repository

import com.gp.common.model.FolderWordList
import com.gp.common.model.WordbookList
import com.gp.network.manager.ApiManager
import com.gp.network.repository.BaseRepository
import com.gp.network.response.BaseResponse

class UserRepo : BaseRepository() {

    suspend fun getWordbookList(currentPage : Int, pageSize : Int, keyword : String) : WordbookList? {
        return requestResponse {
            ApiManager.api.getWordbookList(currentPage, pageSize, keyword)
        }
    }

    suspend fun postWordbookChoose(id : Int) : BaseResponse<Void>? {
        return requestBaseDataResponse {
            ApiManager.api.postWordBookId(id)
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


}