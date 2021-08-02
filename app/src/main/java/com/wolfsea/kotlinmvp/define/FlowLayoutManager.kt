package com.wolfsea.kotlinmvp.define
import android.graphics.Rect
import android.util.SparseArray
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

/**
 *@desc  流布局管理器
 *@author liuliheng
 *@time 2021/6/22  0:15
 **/
class FlowLayoutManager : RecyclerView.LayoutManager() {

    private val allItemsRect = SparseArray<Rect>()

    private var totalHeight = 0
    private var verticalScrollOffset = 0

    override fun onLayoutChildren(recycler: RecyclerView.Recycler?, state: RecyclerView.State?) {

        super.onLayoutChildren(recycler, state)

        if (itemCount <= 0) {
            return
        }

        //isPreLayout主要支持动画,直接跳过
        if (state?.isPreLayout!!) {
            return
        }

        //将视图分离放入scrap缓存中,以准备重新对view进行排版.
        detachAndScrapAttachedViews(recycler!!)

        var offsetX = 0
        var offsetY = 0

        var viewH = 0

        for (i in 0 until itemCount) {

            val child = recycler.getViewForPosition(i)
            addView(child)
            measureChildWithMargins(child, 0, 0)

            val childWidth = getDecoratedMeasuredWidth(child)
            val childHeight = getBottomDecorationHeight(child)

            viewH = childHeight

            val rect = allItemsRect[i] ?: Rect()

            if (offsetX + childWidth > width) {
                //需要换行
                offsetY += childHeight
                offsetX = childWidth
                rect.set(0, offsetY, childWidth, offsetY + childHeight)
            } else {
                //不需要换行
                rect.set(offsetX, offsetY, offsetX + childWidth, offsetY + childHeight)
                offsetX += childWidth
            }

            //存储当前view的Rect
            allItemsRect.put(i, rect)
        }

        totalHeight = viewH + offsetY

        //回收不可见的View
        recyclerViewFillView(recycler, state)
    }


    override fun scrollVerticallyBy(
        dy: Int,
        recycler: RecyclerView.Recycler?,
        state: RecyclerView.State?
    ): Int {

        detachAndScrapAttachedViews(recycler!!)
        //实际滑动距离
        var translateValue = dy

        if (verticalScrollOffset + dy < 0) {
            //第一个坐标值减去以前最后一个坐标值
            translateValue = -verticalScrollOffset
        } else if (verticalScrollOffset + dy > totalHeight - height) {
            //如果滑动底部,往上滑.
            translateValue = totalHeight - height - verticalScrollOffset
        }

        //边界值判断
        verticalScrollOffset += translateValue

        //平移容器内的item
        offsetChildrenVertical(translateValue)

        //回收不可见的View
        recyclerViewFillView(recycler, state)
        return translateValue
    }

    override fun canScrollVertically(): Boolean = true

    override fun generateDefaultLayoutParams(): RecyclerView.LayoutParams =
        RecyclerView.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )

    /**
     *@desc 回收不可见的View
     *@author: liuliheng
     *@time: 2021/6/22 0:37
    **/
    private fun recyclerViewFillView(recycler: RecyclerView.Recycler?, state: RecyclerView.State?) {
        //清空RecyclerView的子view
        detachAndScrapAttachedViews(recycler!!)
        val phoneFrame = Rect(0, verticalScrollOffset, width, verticalScrollOffset + height)
        //将滑出的子view进行回收
        for (i in 0 until itemCount) {

            val child = getChildAt(i)
            val removeChildRect = allItemsRect[i]
            if (!Rect.intersects(phoneFrame, removeChildRect)) {

                removeAndRecycleView(child!!, recycler)
            }
        }

        //可见区域出现在屏幕上的子view
        for (i in 0 until itemCount) {
            if (Rect.intersects(phoneFrame, allItemsRect[i])) {

                //scrap回收池中拿的
                val scrapView = recycler.getViewForPosition(i)
                measureChildWithMargins(scrapView, 0, 0)
                addView(scrapView)
                val childFrame = allItemsRect[i]
                layoutDecorated(
                    scrapView,
                    childFrame.left,
                    childFrame.top - verticalScrollOffset,
                    childFrame.right,
                    childFrame.bottom - verticalScrollOffset
                )
            }
        }
    }

}