package com.wolfsea.kotlinmvp.base2
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel

/**
 *@desc
 *@author liuliheng
 *@time 2021/8/11  23:38
 **/
open class BasePresenter<V : IBaseMvpView> : IBaseMvpPresenter<V> {

    var view: V? = null

    val presenterScope : CoroutineScope by lazy {

        CoroutineScope(Dispatchers.Main + Job())
    }

    override fun attachView(view: V) {

        this.view = view
    }

    override fun detachView() {

        presenterScope.cancel()

        if (this.view != null) {

            this.view = null
        }
    }

}