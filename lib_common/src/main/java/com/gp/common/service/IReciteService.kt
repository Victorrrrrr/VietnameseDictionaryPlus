package com.gp.common.service

import android.content.Context
import com.alibaba.android.arouter.facade.template.IProvider
import com.gp.common.model.LearnWordBean

interface IReciteService : IProvider {

    /**
     * 跳转背诵加载页面
     */
    fun toReciteLoad(context: Context, index: Int)


    fun toReciteWord(context: Context, list: List<LearnWordBean>)

}