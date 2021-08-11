package com.wolfsea.kotlinmvp.coroutinemvp
import com.wolfsea.kotlinmvp.base2.MvpPresenter
import com.wolfsea.kotlinmvp.base2.MvpView
import com.wolfsea.kotlinmvp.bean.Gank

/**
 *@desc  契约
 *@author liuliheng
 *@time 2021/8/11  23:48
 **/
interface CoroutineContract {

    interface View : MvpView {

        fun showLoadingView()

        fun showLoadingSuccessView(ganks: List<Gank>)

        fun showLoadingErrorView()
    }

    interface Presenter : MvpPresenter<View> {

        fun syncWithContext()
    }
}