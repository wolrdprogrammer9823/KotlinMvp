package com.wolfsea.kotlinmvp

import com.wolfsea.kotlinmvp.base.BasePresenter

/**
 *@desc MainPresenter
 *@author liuliheng
 *@time 2021/5/30  14:11
 **/
class MainPresenter : BasePresenter<MainView>() {

    //数据集
    private val dataSet = mutableListOf<String>()
    //子数据集
    private val subDataSet = mutableListOf<String>()

    fun getDataSet(): MutableList<String> = dataSet

    fun getSubDataSet(): MutableList<String> = subDataSet

    fun addData(content: String) {

        dataSet.add(content)

        if (subDataSet.size < 5) {

            subDataSet.add(content)
        }

        mView.refreshList(dataSet, NORMAL)
    }

}