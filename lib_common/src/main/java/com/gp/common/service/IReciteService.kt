package com.gp.common.service

import android.content.Context
import com.alibaba.android.arouter.facade.template.IProvider

interface IReciteService : IProvider {

    /**
     * 跳转背诵加载页面
     */
    fun toReciteLoad(context: Context, index: Int)


}