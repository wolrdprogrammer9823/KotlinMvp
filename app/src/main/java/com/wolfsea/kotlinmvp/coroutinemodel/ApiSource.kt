package com.wolfsea.kotlinmvp.coroutinemodel
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.wolfsea.kotlinmvp.bean.GankResult
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.suspendCancellableCoroutine
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

/**
 *@desc  ApiSource
 *@author liuliheng
 *@time 2021/8/11  23:57
 **/

class ApiSource {

    companion object {

        @JvmField
        val instance: ApiService =
            Retrofit.Builder()
                .baseUrl("http://gank.io/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(ApiService::class.java)

        @JvmField
        val callAdapterApiService: CallAdapterApiService =
            Retrofit.Builder()
                .baseUrl("http://gank.io/api/")
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(CallAdapterApiService::class.java)
    }

}

interface ApiService {

    @GET("data/iOS/2/1")
    fun getIOSGank(): Call<GankResult>

    @GET("data/Android/2/1")
    fun getAndroidGank(): Call<GankResult>

    @GET("data/iOS/2/1")
    suspend fun getSuspendIOSGank(): GankResult

    @GET("data/Android/2/1")
    suspend fun getSuspendAndroidGank(): GankResult
}

interface CallAdapterApiService {

    @GET("data/iOS/2/1")
    fun getIOSGank(): Deferred<GankResult>

    @GET("data/Android/2/1")
    fun getAndroidGank(): Deferred<GankResult>
}

suspend fun <T> Call<T>.await(): T {
    return suspendCancellableCoroutine {
        it.invokeOnCancellation {
            it?.printStackTrace()
            cancel()
        }

        enqueue(object : Callback<T> {
            override fun onResponse(call: Call<T>, response: Response<T>) {
                if (response.isSuccessful) {

                    it.resume(response.body()!!)
                } else {

                    it.resumeWithException(Throwable(response.toString()))
                }
            }

            override fun onFailure(call: Call<T>, t: Throwable) {

                 it.resumeWithException(t)
            }
        })
    }
}