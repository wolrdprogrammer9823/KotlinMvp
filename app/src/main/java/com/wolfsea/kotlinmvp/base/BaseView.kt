package com.wolfsea.kotlinmvp.base

/**
 *@desc  BaseView
 *@author liuliheng
 *@time 2021/5/30  13:36
 **/
interface BaseView<out Presenter : BasePresenter<BaseView<Presenter>>> : IView<Presenter> {


}