package com.gp.network.repository

import com.gp.framework.base.BaseData
import com.gp.framework.base.ReqState

/**
 * 基础仓库
 */
open class BaseRepository {
//    suspend fun <T> requestResponse(requestCall: suspend () -> BaseResponse<T>?): T? {
//        val response = withContext(Dispatchers.IO) {
//            withTimeout(10 * 1000) {
//                requestCall()
//            }
//        } ?: return null
//
//        if(response.isFailed()) {
//            throw ApiException(response.errorCode, response.errorMsg)
//        }
//        return response.data
//    }

    suspend fun <T : Any> executeRequest(block: suspend () -> BaseData<T>) : BaseData<T> {
        val baseData = block.invoke()
        if (baseData.code == 0) {
            // 成功
            baseData.state = ReqState.Success
        } else {
            baseData.state = ReqState.Error
        }
        return baseData
    }

}