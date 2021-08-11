package com.wolfsea.kotlinmvp.coroutinemodel
import com.wolfsea.kotlinmvp.bean.Gank
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.await
import java.lang.Exception

/**
 *@desc
 *@author liuliheng
 *@time 2021/8/12  0:11
 **/
object Repository {

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

}