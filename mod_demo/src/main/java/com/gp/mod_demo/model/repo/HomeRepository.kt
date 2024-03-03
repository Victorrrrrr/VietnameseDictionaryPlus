package com.gp.mod_demo.model.repo

import com.gp.common.model.Article
import com.gp.framework.base.BaseData
import com.gp.network.manager.ApiManager
import com.gp.network.repository.BaseRepository

class HomeRepository : BaseRepository() {

    /**
     * 首页列表
     */
    suspend fun getHomeInfoList(page: Int): BaseData<Article>{
        return executeRequest { ApiManager.api.getHomeList(page) }
    }

}