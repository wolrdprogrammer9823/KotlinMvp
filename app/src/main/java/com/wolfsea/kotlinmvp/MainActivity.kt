package com.wolfsea.kotlinmvp
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.wolfsea.kotlinmvp.base.BaseMvpActivity
import com.wolfsea.kotlinmvp.divider.DividerItemDecoration
import com.wolfsea.kotlinmvp.drakeer.MultiTypeAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseMvpActivity<MainPresenter>(), MainView {

    private val mDataSource = mutableListOf<String>()
    private lateinit var mAdapter: MultiTypeAdapter

    private var mIsExpand = true

    override fun getLayoutId(): Int = R.layout.activity_main

    override fun initView() {
        initRv()
    }

    override fun initData(savedInstanceState: Bundle?) {

    }

    override fun initEvent() {

        add_btn.setOnClickListener {
            val content = content_et.text.toString().trim()
            mPresenter.addData(content)
        }

        load_more_tv.setOnClickListener {
            showDataSet()
        }

        load_more_arrow_iv.setOnClickListener {
            showDataSet()
        }
    }

    override fun refreshList(
        dataSet: MutableList<String>,
        code: Int
    ) {

        mDataSource.clear()
        when (code) {
            NORMAL -> {

                val elementOver5 = dataSet.size > 5
                if (elementOver5) {
                    mDataSource.addAll(dataSet.subList(0, 5))
                } else {
                    mDataSource.addAll(dataSet)
                }
                setViewVisibility(elementOver5)
            }
            EXPAND -> {

                mDataSource.addAll(dataSet)
                setViewVisibility(dataSet.size > 5)
            }
            HIDE -> {

                mDataSource.addAll(dataSet)
                setViewVisibility(true)
            }
            else -> {}
        }
        mAdapter.notifyDataSetChanged()
    }

    /**
     *@desc 初始化Rv
     *@author:liuliheng
     *@time: 2021/5/30 18:10
     **/
    private fun initRv() {
        val itemViewBinder = MainRvBinder(this)
        mAdapter = MultiTypeAdapter(mDataSource)
        mAdapter.register(String::class.java, itemViewBinder)
        content_rv.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            addItemDecoration(
                DividerItemDecoration(
                    this@MainActivity,
                    1.0F,
                    context.resources.getColor(R.color.purple_200))
                )
            adapter = mAdapter
        }
    }

    private fun setViewVisibility(visible: Boolean) {
        load_more_arrow_iv.visibility = if (visible) View.VISIBLE else View.GONE
        load_more_tv.visibility = if (visible) View.VISIBLE else View.GONE
    }

    private fun updateDataSet(dataSet: MutableList<String>, code: Int) {
        refreshList(dataSet, code)
    }

    private fun showDataSet() {
        if (mIsExpand) {
            updateDataSet(mPresenter.getDataSet(), EXPAND)
        } else {
            updateDataSet(mPresenter.getSubDataSet(), HIDE)
        }
        updateText(mIsExpand)
        mIsExpand = !mIsExpand
    }

    private fun updateText(expand: Boolean) {
        load_more_tv.text = if (expand) "收起" else "展开"
        load_more_arrow_iv.setImageResource(if (expand) R.drawable.ic_arrow_downward else R.drawable.ic_arrow_upward)
    }
}