package com.wolfsea.kotlinmvp
import android.view.View
import android.view.ViewGroup
import androidx.collection.SparseArrayCompat
import androidx.recyclerview.widget.RecyclerView

/**
 *@desc  RvAdapter包装
 *@author liuliheng
 *@time 2021/6/10  23:28
 **/
class HeaderAndFooterWrapper<T>(innerAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val mInnerAdapter = innerAdapter

    private var mHeaderViews = SparseArrayCompat<View>()
    private var mFooterViews = SparseArrayCompat<View>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (mHeaderViews.get(viewType) != null) {

            return ViewHolder(mHeaderViews.get(viewType)!!)
        } else if (mFooterViews.get(viewType) != null) {

            return ViewHolder(mFooterViews.get(viewType)!!)
        }

        return mInnerAdapter.onCreateViewHolder(parent, viewType)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (mIsHeaderViewPosition(position)) {
            return
        }

        if (mIsFooterViewPosition(position)) {
            return
        }

        mInnerAdapter.onBindViewHolder(holder, position - getHeaderCount())
    }

    override fun getItemCount(): Int = getHeaderCount() + getRealItemCount() + getFooterCount()

    override fun getItemViewType(position: Int): Int {
        if (mIsHeaderViewPosition(position)) {

            return mHeaderViews.keyAt(position)
        } else if (mIsFooterViewPosition(position)) {

            return mFooterViews.keyAt(position)
        }

        return mInnerAdapter.getItemViewType(position - getHeaderCount())
    }

    fun addHeaderView(headerView: View) {
        mHeaderViews.put(BASE_ITEM_VIEW_HEADER + getHeaderCount(), headerView)
    }

    fun addFooterView(footerView: View) {
        mFooterViews.put(BASE_ITEM_VIEW_FOOTER + getFooterCount(), footerView)
    }

    private fun mIsHeaderViewPosition(position: Int): Boolean = position < getHeaderCount()

    private fun mIsFooterViewPosition(position: Int): Boolean = position >= getHeaderCount() + getRealItemCount()

    private fun getHeaderCount() = mHeaderViews.size()

    private fun getFooterCount() = mFooterViews.size()

    private fun getRealItemCount() = mInnerAdapter.itemCount

    companion object {
        const val BASE_ITEM_VIEW_HEADER = 1000000
        const val BASE_ITEM_VIEW_FOOTER = 2000000
    }
}