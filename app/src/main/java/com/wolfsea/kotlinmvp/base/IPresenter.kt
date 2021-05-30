package com.wolfsea.kotlinmvp.base

/**
 *@desc Presenter
 *@author liuliheng
 *@time 2021/5/30  13:20
 **/
interface IPresenter<out View : IView<IPresenter<View>>> {

    val mView: View
}