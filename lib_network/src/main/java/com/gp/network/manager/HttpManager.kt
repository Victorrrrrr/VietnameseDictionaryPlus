package com.gp.network.manager

import android.util.Log
import com.gp.framework.helper.VDHelper
import com.gp.framework.utils.NetworkUtil
import com.gp.network.api.ApiService
import com.gp.network.constant.BASE_URL
import com.gp.network.error.ERROR
import com.gp.network.error.NoNetWorkException
import com.gp.network.interceptor.HeaderInterceptor
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import kotlin.math.log

object HttpManager {
    private val mRetrofit: Retrofit

    init {
        mRetrofit = Retrofit.Builder()
            .client(initOkHttpClient())
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


    /**
     * 获取 apiService
     */
    fun <T> create(apiService: Class<T>): T {
        return mRetrofit.create(apiService)
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