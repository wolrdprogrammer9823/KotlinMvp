package com.wolfsea.kotlinmvp.base
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import java.lang.IllegalArgumentException
import java.lang.reflect.ParameterizedType

/**
 *@desc BaseMvpActivity
 *@author:liuliheng
 *@time: 2021/5/30 13:42
**/
abstract class BaseMvpActivity<Presenter : BasePresenter<BaseView<Presenter>>>
                     : AppCompatActivity(), BaseView<Presenter> {

    final override val mPresenter: Presenter

    init {
        mPresenter = findPresenterClass().newInstance()
        mPresenter.mView = this
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        initView()
        initEvent()
        initData(savedInstanceState)
    }

    override fun showToast(message: String) {

    }

    override fun showLoading(color: Int, tip: String) {

    }

    override fun hideLoading() {

    }

    override fun onError(throwable: Throwable) {

    }

    /**
     *@desc 通过反射找打Presenter类
     *@author:liuliheng
     *@time: 2021/5/30 14:05
    **/
    private fun findPresenterClass(): Class<Presenter> {
        var thisClass: Class<*> = this.javaClass
        while (true) {
            (thisClass.genericSuperclass as? ParameterizedType)?.actualTypeArguments?.firstOrNull()
                ?.let {

                   return it as Class<Presenter>
                }
                ?: run {

                  thisClass = thisClass.superclass ?: throw IllegalArgumentException()
               }
        }
    }

    abstract fun getLayoutId() : Int

    /**
     *@desc 初始化事件
     *@author:liuliheng
     *@time: 2021/5/30 18:12
    **/
    abstract fun initEvent()

    /**
     *@desc 初始化View
     *@author:liuliheng
     *@time: 2021/5/30 18:12
    **/
    abstract fun initView()

    /**
     *@desc 初始化数据
     *@author:liuliheng
     *@time: 2021/5/30 18:12
    **/
    abstract fun initData(savedInstanceState: Bundle?)
}