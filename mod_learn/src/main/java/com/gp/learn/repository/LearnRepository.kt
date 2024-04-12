package com.gp.learn.repository

import com.gp.common.model.AddWordToFolderRequest
import com.gp.common.model.BaiduTranslateResponse
import com.gp.common.model.BaiduVoiceRequest
import com.gp.common.model.FolderList
import com.gp.common.model.FolderAddRequest
import com.gp.common.model.FolderWordList
import com.gp.common.model.WordLearnList
import com.gp.framework.ext.toJson
import com.gp.framework.utils.LogUtil
import com.gp.network.manager.ApiManager
import com.gp.network.manager.TokenManager
import com.gp.network.repository.BaseRepository
import com.gp.network.response.BaseResponse

class LearnRepository : BaseRepository(){

    suspend fun sendVoiceTranslate(
        fromLanguage: String,
        toLanguage: String,
        voice: String,
        format: String
    ): BaiduTranslateResponse? {
        return requestAuthResponse {
            val request = BaiduVoiceRequest(fromLanguage, toLanguage, voice, format)
            val token = TokenManager.getBaiduToken()
            ApiManager.baidu.voiceTranslate(token?:"", request)
        }
    }

    suspend fun getLearnWord(size : Int) : BaseResponse<WordLearnList>? {
        return requestBaseDataResponse {
            ApiManager.api.getLearnWord(size)
        }
    }


    suspend fun getFolderList(currentPage: Int, pageSize: Int) : FolderList? {
        return requestResponse {
            ApiManager.api.getFolderList(currentPage, pageSize)
        }
    }


    suspend fun addFolder(body : FolderAddRequest) : BaseResponse<Void>? {
        LogUtil.d(body.toJson())
        return requestBaseDataResponse {
            ApiManager.api.newFolder(body)
        }
    }


    suspend fun deleteFolder(folderId : Int) : BaseResponse<Void>? {
        return requestBaseDataResponse {
            ApiManager.api.deleteFolder(folderId)
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