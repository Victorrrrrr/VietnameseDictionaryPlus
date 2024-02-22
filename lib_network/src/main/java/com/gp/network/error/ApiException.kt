package com.gp.network.error

import java.io.IOException
import java.lang.Exception

open class ApiException : Exception{
    var errCode: Int
    var errMsg : String

    constructor(error: ERROR, e: Throwable? = null) : super(e) {
        errCode = error.code
        errMsg = error.errMsg
    }

    constructor(code: Int, msg: String, e: Throwable? = null) : super(e) {
        this.errCode = code
        this.errMsg = msg
    }
}


/**
 * 无网络连接异常
 */
class NoNetWorkException : IOException {
    var errCode: Int
    var errMsg: String

    constructor(error: ERROR, e: Throwable? = null) : super(e) {
        errCode = error.code
        errMsg = error.errMsg
    }
}

