package com.gp.framework.base

import com.google.gson.annotations.SerializedName

class BaseData<T> {
    @SerializedName("code")
    var code = -1

    @SerializedName("msg")
    var msg: String? = null

    var data: T? = null

    var state: ReqState = ReqState.Error

    override fun toString(): String {
        return "BaseData(code=$code, msg=$msg, data=$data, state=$state)"
    }

}


enum class ReqState {
    Success, Error
}