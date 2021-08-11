package com.wolfsea.kotlinmvp.coroutinemodel
import com.wolfsea.kotlinmvp.bean.GankResult
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

/**
 *@desc  ApiSource
 *@author liuliheng
 *@time 2021/8/11  23:57
 **/

class ApiSource {

    companion object {

        @JvmField
        val instance =
            Retrofit.Builder()
                .baseUrl("http://gank.io/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(ApiService::class.java)
    }
}

interface ApiService {

    @GET("data/iOS/2/1")
    fun getIOSGank(): Call<GankResult>

    @GET("data/Android/2/1")
    fun getAndroidGank(): Call<GankResult>
}