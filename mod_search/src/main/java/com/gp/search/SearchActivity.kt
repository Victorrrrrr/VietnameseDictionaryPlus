package com.gp.search

import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.gp.common.constant.SEARCH_SERVICE_SEARCH
import com.gp.framework.base.BaseMvvmActivity
import com.gp.mod_search.databinding.ActivitySearchBinding



@Route(path = SEARCH_SERVICE_SEARCH)
class SearchActivity : BaseMvvmActivity<ActivitySearchBinding, SearchViewModel>() {
    override fun initView(savedInstanceState: Bundle?) {

    }

}