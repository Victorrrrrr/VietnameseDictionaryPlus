package com.gp.network.response

data class BaseResponse<out T>(
    val errorCode: Int = 0,  // 服务器状态码 这里0表示请求成功
    val errorMsg: String = "", // 错误信息
    val data: T?
) {

    /**
     * 判定接口返回是否正常
     */
    fun isFailed() : Boolean {
        return errorCode != 0
    }

}
