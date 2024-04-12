package com.gp.common.provider

import android.content.Context
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.launcher.ARouter
import com.gp.common.constant.SEARCH_SERVICE_SEARCH
import com.gp.common.service.ISearchService

object SearchServiceProvider {

    @Autowired(name = SEARCH_SERVICE_SEARCH)
    lateinit var searchService: ISearchService

    init {
        ARouter.getInstance().inject(this)
    }

    /**
     * 跳转搜索页面
     *
     * @param context
     * @param index
     */
    fun toSearch(context: Context) {
        searchService.toSearch(context)
    }

    fun toSearchWord(context: Context, str: String) {
        searchService.toSearch(context, str)
    }


    fun toWordPage(context: Context, id: String) {
        searchService.toWordPage(context, id)
    }


}