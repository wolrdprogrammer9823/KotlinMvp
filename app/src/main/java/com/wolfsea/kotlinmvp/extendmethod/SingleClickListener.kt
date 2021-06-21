package com.wolfsea.kotlinmvp.extendmethod
import android.view.View
import java.util.*

/**
 *@desc  单一点击事件
 *@author liuliheng
 *@time 2021/6/15  22:31
 **/
abstract class SingleClickListener : View.OnClickListener {

    companion object {
        const val MIN_CLICK_DELAY_TIME = 500
    }

    private var lastClickTime: Long = 0

    override fun onClick(v: View?) {

        val currentTime = Calendar.getInstance().timeInMillis
        if (currentTime - lastClickTime > MIN_CLICK_DELAY_TIME) {

            lastClickTime = currentTime
            onSingleClick(v)
        }
    }

    abstract fun onSingleClick(view: View?)
}