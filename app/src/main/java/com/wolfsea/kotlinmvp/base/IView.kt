package com.wolfsea.kotlinmvp.base
import android.graphics.Color

/**
 *@desc  View
 *@author liuliheng
 *@time 2021/5/30  13:18
 **/
interface IView<out Presenter : IPresenter<IView<Presenter>>> {

    val mPresenter: Presenter

    fun showToast(message: String)

    fun showLoading(color: Int = Color.BLUE, tip: String = "正在加载中...")

    fun hideLoading()

    fun onError(throwable: Throwable)
}