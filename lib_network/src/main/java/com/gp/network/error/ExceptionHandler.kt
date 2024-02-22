package com.gp.network.error

import com.gp.framework.toast.TipsToast


/**
 * 统一错误处理工具
 */
object ExceptionHandler {

    fun handleException(e: Throwable): ApiException {

        val ex: ApiException
        if (e is ApiException) {
            ex = ApiException(e.errCode, e.errMsg, e)
//            if (ex.errCode == ERROR.)
        } else if(e is NoNetWorkException) {
            TipsToast.showTips("网络异常，请重新刷新")
            ex = ApiException(ERROR.FAIL, e)
        }



        else {
            ex = ApiException(ERROR.FAIL, e)
        }





        return ex

    }


}