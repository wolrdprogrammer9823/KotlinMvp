package com.wolfsea.kotlinmvp.base2
import androidx.annotation.UiThread

/**
 *@desc  Presenter
 *@author liuliheng
 *@time 2021/8/11  23:35
 **/
interface MvpPresenter<V : MvpView> {

    @UiThread
    fun attachView(view:V)

    @UiThread
    fun detachView()
}