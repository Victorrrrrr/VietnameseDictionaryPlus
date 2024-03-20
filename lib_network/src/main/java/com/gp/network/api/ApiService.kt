package com.gp.network.api

import com.gp.common.model.Article
import com.gp.common.model.ArticleList
import com.gp.common.model.AuthClientBean
import com.gp.common.model.AuthPasswordBean
import com.gp.common.model.DailyHomeBean
import com.gp.common.model.MusicBean
import com.gp.common.model.PersonBean
import com.gp.common.model.PersonDaily
import com.gp.common.model.SceneryBean
import com.gp.common.model.UserInfo
import com.gp.common.model.WordDetail
import com.gp.common.model.WordRandomBean
import com.gp.framework.base.BaseData
import com.gp.network.response.BaseResponse
import retrofit2.http.Body
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
    @Deprecated("测试api")
    @GET("article/list/{page}/json")
    suspend fun getHomeList(
        @Path("page") page: Int,
    ): BaseData<Article>

    /**
     * wanAndroid demo测试
     * 首页资讯
     * @param page    页码
     * @param pageSize 每页数量
     */
    @Deprecated("测试api")
    @GET("article/list/{page}/json")
    suspend fun getHomeList(
        @Path("page") page: Int,
        @Query("page_size") pageSize: Int
    ): BaseResponse<ArticleList>?



    @FormUrlEncoded
    @POST("app-auth/oauth/token")
    suspend fun appAuthClient(
        @Field("grant_type") grantType : String,
        @Field("client_id") clientId : String,
        @Field("client_secret") clientSecret : String
    ) : AuthClientBean


    @FormUrlEncoded
    @POST("app-auth/oauth/token")
    suspend fun appAuthPassword(
        @Field("grant_type") grantType : String,
        @Field("username") username : String,
        @Field("password") password : String
    ) : AuthPasswordBean


    @POST("app-auth/auth/register")
    suspend fun appAuthRegister(
        @Body userInfo: UserInfo
    ) : BaseResponse<Void>


    @GET("app-dict/daily")
    suspend fun getDailyHomeData() : BaseResponse<DailyHomeBean>


    @GET("app-dict/word/random")
    suspend fun getWordRandom() : BaseResponse<WordRandomBean>

    @GET("app-dict/daily/character")
    suspend fun getPersonDaily() : BaseResponse<PersonBean>


    @GET("app-dict/daily/scenery")
    suspend fun getSceneryDaily() : BaseResponse<SceneryBean>


    @GET("app-dict/daily/music")
    suspend fun getMusicDaily() : BaseResponse<MusicBean>


    @GET("/app-dict/word/{id}")
    suspend fun getWordDetail(@Path("id")id : String) : BaseResponse<WordDetail>

}