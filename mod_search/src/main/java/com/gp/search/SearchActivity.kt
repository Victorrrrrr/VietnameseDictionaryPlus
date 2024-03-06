package com.gp.search

import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.gp.common.constant.SEARCH_ACTIVITY_SEARCH
import com.gp.framework.base.BaseMvvmActivity
import com.gp.mod_search.databinding.ActivitySearchBinding



@Route(path = SEARCH_ACTIVITY_SEARCH)
class SearchActivity : BaseMvvmActivity<ActivitySearchBinding, SearchViewModel>() {
    override fun initView(savedInstanceState: Bundle?) {

    }


    override fun finish() {
        super.finish()
        // 取消退出动画
        overridePendingTransition(0,0)
    }

}