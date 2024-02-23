package com.gp.network.error

enum class ERROR(val code: Int, val errMsg: String) {

    /**
     * 对应HTTP的状态码
     */

    FAIL(400, "失败"),
    PERMISSION_DENIED(401, "权限不足"),
    PARAM_ERROR(402, "参数错误"),
    UNAUTHORIZED(403, "未认证"),
    SERVER_ERROR(500, "服务器内部错误"),


    UNKNOWN(1000, "未知错误"),
    PARSE_ERROR(1001, "解析错误"),
    NETWORK_ERROR(1002, "网络异常，请尝试刷新"),
    HTTP_ERROR(1003, "404 Not Found"),
    SSL_ERROR(1004, "证书出错"),
    TIMEOUT_ERROR(1005, "连接超时"),
    UNLOGIN(-1001, "未登录"),
    UNKNOWN_HOST(1007, "未知Host")

}