package com.wolfsea.kotlinmvp.coroutinemvp
import com.wolfsea.kotlinmvp.base2.BasePresenter
import com.wolfsea.kotlinmvp.coroutinemodel.Repository
import kotlinx.coroutines.launch
import java.lang.Exception

/**
 *@desc  CoroutinePresenter
 *@author liuliheng
 *@time 2021/8/11  23:52
 **/
class CoroutinePresenter : CoroutineContract.Presenter, BasePresenter<CoroutineContract.View>() {

    override fun syncWithContext() {
        presenterScope.launch {

            view.showLoadingView()
            try {
                val ganks = Repository.querySyncWithContext()
                view.showLoadingSuccessView(ganks = ganks)
            } catch (e: Exception) {
                e.printStackTrace()
                view.showLoadingErrorView()
            } finally {}
        }
    }

    override fun syncNoneWithContext() {
        presenterScope.launch {

            view.showLoadingView()
            try {
                val ganks = Repository.querySyncNoneWithContext()
                view.showLoadingSuccessView(ganks = ganks)
            } catch (e: Exception) {
                e.printStackTrace()
                view.showLoadingErrorView()
            } finally {}
        }
    }

    override fun asyncWithContextForAwait() {
        presenterScope.launch {
            view.showLoadingView()
            try {
                val ganks = Repository.queryAsyncWithContextForAwait()
                view.showLoadingSuccessView(ganks = ganks)
            } catch (e: Exception) {
                e.printStackTrace()
                view.showLoadingErrorView()
            } finally {}
        }
    }

    override fun adapterCoroutineQuery() {
        presenterScope.launch {
            view.showLoadingView()
            try {
                val ganks = Repository.adapterCoroutineQuery()
                view.showLoadingSuccessView(ganks = ganks)
            } catch (e: Exception) {
                e.printStackTrace()
                view.showLoadingErrorView()
            } finally {}
        }
    }

    override fun retrofitSuspendQuery() {
        presenterScope.launch {
            view.showLoadingView()
            try {
                val ganks = Repository.retrofitSuspendQuery()
                view.showLoadingSuccessView(ganks = ganks)
            } catch (e: Exception) {
                e.printStackTrace()
                view.showLoadingErrorView()
            } finally {}
        }
    }

}