package com.gp.search.service

import android.content.Context
import android.content.Intent
import com.alibaba.android.arouter.facade.annotation.Route
import com.gp.common.constant.SEARCH_SERVICE_SEARCH
import com.gp.common.service.ISearchService
import com.gp.network.constant.KEY_ID
import com.gp.search.ui.search.SearchActivity
import com.gp.search.ui.word.WordPageActivity

@Route(path = SEARCH_SERVICE_SEARCH)
class SearchService : ISearchService{
    override fun toSearch(context: Context) {
        val intent = Intent(context, SearchActivity::class.java)
        // 取消翻页动画
        intent.flags = Intent.FLAG_ACTIVITY_NO_ANIMATION
        context.startActivity(intent)
    }


    override fun toWordPage(context: Context, id: String) {
        val intent = Intent(context, WordPageActivity::class.java)
        intent.putExtra(KEY_ID, id)
        context.startActivity(intent)
    }


    override fun init(context: Context?) {

    }


}