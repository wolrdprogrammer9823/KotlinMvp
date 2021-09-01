package com.wolfsea.kotlinmvp.coroutinemvp
import com.wolfsea.kotlinmvp.base2.IBaseMvpPresenter
import com.wolfsea.kotlinmvp.base2.IBaseMvpView
import com.wolfsea.kotlinmvp.bean.Gank

/**
 *@desc  契约
 *@author liuliheng
 *@time 2021/8/11  23:48
 **/
interface CoroutineContract {

    interface View : IBaseMvpView {

        fun showLoadingView()

        fun showLoadingSuccessView(ganks: List<Gank>)

        fun showLoadingErrorView()
    }

    interface Presenter : IBaseMvpPresenter<View> {

        fun syncWithContext()

        fun syncNoneWithContext()

        fun asyncWithContextForAwait()

        fun adapterCoroutineQuery()

        fun retrofitSuspendQuery()
    }
}