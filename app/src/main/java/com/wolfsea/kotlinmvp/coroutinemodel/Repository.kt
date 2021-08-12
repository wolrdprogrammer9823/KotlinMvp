package com.wolfsea.kotlinmvp.coroutinemodel
import com.wolfsea.kotlinmvp.bean.Gank
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import java.lang.Exception

/**
 *@desc  Model类
 *@author liuliheng
 *@time 2021/8/12  0:11
 **/
object Repository {

    /*
    *  两个请求在子线程中顺序执行,非同时并发.
    * */
    suspend fun querySyncWithContext(): List<Gank> = withContext(Dispatchers.Main) {
        try {

            val androidResult = ApiSource.instance.getAndroidGank().await()
            val iosResult = ApiSource.instance.getIOSGank().await()

            val result = mutableListOf<Gank>().apply {
                addAll(androidResult.results)
                addAll(iosResult.results)
            }

            result
        } catch (e: Exception) {

           e.printStackTrace()
           throw e
        }
    }

    /*
    *  两个请求在主线中顺序执行,非同时并发.
    * */
    suspend fun querySyncNoneWithContext() : List<Gank> {
        return try {

            val androidResult = ApiSource.instance.getAndroidGank().await()
            val iosResult = ApiSource.instance.getIOSGank().await()

            val result = mutableListOf<Gank>().apply {
                addAll(androidResult.results)
                addAll(iosResult.results)
            }

            result
        } catch (e: Exception) {

            e.printStackTrace()
            throw e
        }
    }

    /*
    * 两个请求在子线程中并发执行
    * */
    suspend fun queryAsyncWithContextForAwait(): List<Gank> = withContext(Dispatchers.Main) {
        try {

            val androidDeferred = async {
                val androidResult = ApiSource.instance.getAndroidGank().await()
                androidResult
            }

            val iosDeferred = async {
                val iosResult = ApiSource.instance.getIOSGank().await()
                iosResult
            }

            val androidResult = androidDeferred.await().results
            val iosResult = iosDeferred.await().results

            val result = mutableListOf<Gank>().apply {
                addAll(androidResult)
                addAll(iosResult)
            }

            result
        } catch (e: Exception) {

            e.printStackTrace()
            throw e
        }
    }

    /*
    *  Adapter适配协程请求
    * */
    suspend fun adapterCoroutineQuery(): List<Gank> = withContext(Dispatchers.Main) {
        try {

            val androidDeferred = ApiSource.callAdapterApiService.getAndroidGank()
            val iosDeferred = ApiSource.callAdapterApiService.getIOSGank()

            val androidResult = androidDeferred.await().results
            val iosResult = iosDeferred.await().results

            val result = mutableListOf<Gank>().apply {
                addAll(androidResult)
                addAll(iosResult)
            }

            result
        } catch (e: Exception) {

            e.printStackTrace()
            throw e
        }
    }

    /*
    *  retrofit协程官方支持
    * */
    suspend fun retrofitSuspendQuery(): List<Gank> = withContext(Dispatchers.Main) {
        try {

            val androidResult = ApiSource.instance.getSuspendAndroidGank()
            val iosResult = ApiSource.instance.getSuspendIOSGank()

            val result = mutableListOf<Gank>().apply {
                addAll(androidResult.results)
                addAll(iosResult.results)
            }

            result
        } catch (e: Exception) {
            e.printStackTrace()
            throw e
        }
    }

}