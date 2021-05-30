package com.wolfsea.kotlinmvp
import com.wolfsea.kotlinmvp.base.BaseView

/**
 *@desc  MainView
 *@author liuliheng
 *@time 2021/5/30  14:11
 **/
interface MainView : BaseView<MainPresenter> {

    fun refreshList(
        dataSet: MutableList<String>,
        code: Int
    )
}