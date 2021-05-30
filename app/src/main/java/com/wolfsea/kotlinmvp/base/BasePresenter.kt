package com.wolfsea.kotlinmvp.base

/**
 *@desc  抽象Presenter
 *@author liuliheng
 *@time 2021/5/30  13:32
 **/
abstract class BasePresenter<out View : IView<BasePresenter<View>>> : IPresenter<View> {

    override lateinit var mView: @UnsafeVariance View
}