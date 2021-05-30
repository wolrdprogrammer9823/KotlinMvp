package com.wolfsea.kotlinmvp.divider
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 *@desc 分割线
 *@author liuliheng
 *@time 2021/5/30  22:33
 **/
class DividerItemDecoration(context: Context, lineHeight: Float, lineColor: Int) : RecyclerView.ItemDecoration() {

    private val mLineHeight: Int
    private val paint: Paint = Paint()

    init {
        paint.isAntiAlias = true
        //分割线颜色
        paint.color = lineColor
        //线高
        mLineHeight = dp2px(context, lineHeight).toInt()
    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        outRect.bottom = mLineHeight
    }

    override fun onDrawOver(canvas: Canvas, parent: RecyclerView, state: RecyclerView.State) {

        super.onDrawOver(canvas, parent, state)

        for (childIndex in 0.until(parent.childCount)) {

            val child = parent.getChildAt(childIndex)
            val layoutParams = child.layoutParams as RecyclerView.LayoutParams
            val top = child.bottom.toFloat() + layoutParams.bottomMargin.toFloat()
            val bottom = top + mLineHeight
            val left = child.left.toFloat()
            val right = left + child.width.toFloat()
            canvas.drawRect(left, top, right, bottom, paint)
        }
    }

    private fun dp2px(context: Context, dps: Float): Float {
        return context.resources.displayMetrics.density.times(dps)
    }
}