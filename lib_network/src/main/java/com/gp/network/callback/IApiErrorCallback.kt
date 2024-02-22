package com.gp.network.callback

import com.gp.framework.toast.TipsToast

/**
 * 接口请求错误接口
 */
interface IApiErrorCallback {

    /**
     * 错误回调处理
     */
    fun onError(code : Int?, error: String?) {
        TipsToast.showTips(error)
    }


    /**
     * 登录失效处理
     */
    fun onLoginFail(code: Int?, error: String?) {
        TipsToast.showTips(error)
    }

}