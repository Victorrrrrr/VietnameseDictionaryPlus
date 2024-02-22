package com.gp.network.error

enum class ERROR(val code: Int, val errMsg: String) {

    /**
     * 对应HTTP的状态码
     */

    FAIL(400, "失败"),
    PERMISSION_DENIED(401, "权限不足"),
    PARAM_ERROR(402, "参数错误"),
    UNAUTHORIZED(403, "未认证"),
    SERVER_ERROR(500, "服务器内部错误")

}