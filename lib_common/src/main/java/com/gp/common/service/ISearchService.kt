package com.gp.common.service

import android.content.Context
import com.alibaba.android.arouter.facade.template.IProvider

interface ISearchService : IProvider {

    /**
     * 跳转搜索页
     *
     * @param context
     * @param index
     */
    fun toSearch(context: Context, index: Int)

}