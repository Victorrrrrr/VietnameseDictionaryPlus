package com.gp.network.manager

import android.util.Log
import com.gp.framework.helper.VDHelper
import com.gp.framework.utils.NetworkUtil
import com.gp.network.constant.BAIDU_URL
import com.gp.network.constant.BASE_URL
import com.gp.network.error.ERROR
import com.gp.network.error.NoNetWorkException
import com.gp.network.interceptor.HeaderInterceptor
import com.gp.network.interceptor.TokenExpireInterceptor
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object HttpManager {
    private var mRetrofit: Retrofit? = null
    private var baiduRetrofit: Retrofit? = null
    private var urlRetrofitMap : HashMap<String, Retrofit>

    init {
        urlRetrofitMap = HashMap()
        mRetrofit = Retrofit.Builder()
            .client(initOkHttpClient())
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        urlRetrofitMap.put(BASE_URL, mRetrofit!!)

        baiduRetrofit = Retrofit.Builder()
            .client(initOkHttpClient())
            .baseUrl(BAIDU_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        urlRetrofitMap.put(BAIDU_URL, baiduRetrofit!!)
    }

    /**
     * 获取 apiService
     */
    fun <T> create(apiService: Class<T>, baseUrl : String): T {
        return urlRetrofitMap[baseUrl]!!.create(apiService)
    }


    /**
     * 初始化OkHttp
     */
    private fun initOkHttpClient(): OkHttpClient {
        val build = OkHttpClient.Builder()
            .connectTimeout(12, TimeUnit.SECONDS)
            .writeTimeout(12, TimeUnit.SECONDS)
            .readTimeout(12, TimeUnit.SECONDS)

        // 添加参数拦截器
        val interceptor = mutableListOf<Interceptor>()

        build.addInterceptor(HeaderInterceptor())
        build.addInterceptor(TokenExpireInterceptor())

        // 日志拦截器
        val logInterceptor = HttpLoggingInterceptor { message : String ->
            Log.i("okhttp", "data:$message")
        }

        if (VDHelper.isDebug()) {
            logInterceptor.level = HttpLoggingInterceptor.Level.BODY
        } else {
            logInterceptor.level = HttpLoggingInterceptor.Level.BASIC
        }
        build.addInterceptor(logInterceptor)
        build.addInterceptor(object : Interceptor {
            override fun intercept(chain: Interceptor.Chain): Response {
                if(NetworkUtil.isConnected(VDHelper.getApplication())) {
                    val request = chain.request()
                    return chain.proceed(request)
                } else {
                    throw NoNetWorkException(ERROR.NETWORK_ERROR)
                }
            }
        })

        return build.build()
    }

}