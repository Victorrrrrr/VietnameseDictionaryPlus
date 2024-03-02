package com.gp.network.api

import com.gp.common.model.Article
import com.gp.common.model.ArticleList
import com.gp.common.model.AuthBean
import com.gp.framework.base.BaseData
import com.gp.network.response.BaseResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded

import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

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

    /**
     * 首页资讯
     * @param page    页码
     * @param pageSize 每页数量
     */
    @GET("article/list/{page}/json")
    suspend fun getHomeList(
        @Path("page") page: Int,
        @Query("page_size") pageSize: Int
    ): BaseResponse<ArticleList>?


    @FormUrlEncoded
    @POST("app-auth/oauth/token")
    suspend fun appAuthToken(
        @Field("grant_type") grantType : String,
        @Field("username") clientId : String,
        @Field("password") clientSecret : String
    ) : AuthBean

}