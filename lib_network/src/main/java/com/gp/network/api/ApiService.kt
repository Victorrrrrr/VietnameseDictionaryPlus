package com.gp.network.api

import com.gp.common.model.Article
import com.gp.framework.base.BaseData

import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Api接口类
 */
interface ApiService {

    /**
     * wanAndroid demo测试
     * 首页资讯
     * @param page    页码
     */
    @GET("article/list/{page}/json")
    suspend fun getHomeList(
        @Path("page") page: Int,
    ): BaseData<Article>

}