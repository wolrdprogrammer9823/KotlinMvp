package com.wolfsea.kotlinmvp.coroutinecase
import android.os.Bundle
import com.wolfsea.kotlinmvp.R
import com.wolfsea.kotlinmvp.base2.BaseMvpActivity
import com.wolfsea.kotlinmvp.bean.Gank
import com.wolfsea.kotlinmvp.coroutinemvp.CoroutineContract
import com.wolfsea.kotlinmvp.coroutinemvp.CoroutinePresenter
import com.wolfsea.kotlinmvp.extendmethod.hideSelf
import com.wolfsea.kotlinmvp.extendmethod.showSelf
import com.wolfsea.kotlinmvp.extendmethod.toast
import kotlinx.android.synthetic.main.activity_coroutine_case.*

class CoroutineCaseActivity : BaseMvpActivity<CoroutineContract.View, CoroutinePresenter>(), CoroutineContract.View {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coroutine_case)
        initEvent()
    }

    override fun showLoadingView() {
         loadingBar.showSelf()
    }

    override fun showLoadingSuccessView(ganks: List<Gank>) {

        loadingBar.hideSelf()
        textView.text = "请求结束，数据条数：${ganks.size}"
        toast("请求成功")
    }

    override fun showLoadingErrorView() {
        loadingBar.hideSelf()
        toast("请求失败")
    }

    private fun initEvent() {

        syncWithContextBtn.setOnClickListener {
            presenter.syncWithContext()
        }

        syncNoneWithContext.setOnClickListener {
            presenter.syncNoneWithContext()
        }

        asyncWithContextForAwait.setOnClickListener {
            presenter.asyncWithContextForAwait()
        }

        adapterBtn.setOnClickListener {
           presenter.adapterCoroutineQuery()
        }

        retrofitBtn.setOnClickListener {
            presenter.retrofitSuspendQuery()
        }
    }

}