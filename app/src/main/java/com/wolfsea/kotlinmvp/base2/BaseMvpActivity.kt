package com.wolfsea.kotlinmvp.base2
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import java.lang.ClassCastException
import java.lang.reflect.ParameterizedType

abstract class BaseMvpActivity<V : IBaseMvpView, P : BasePresenter<V>> : AppCompatActivity() {

    lateinit var presenter: P

    override fun onCreate(savedInstanceState: Bundle?) {

        presenter = findPresenterClass()!!
        presenter.attachView(this as V)

        super.onCreate(savedInstanceState)
    }

    override fun onDestroy() {

        presenter.detachView()
        super.onDestroy()
    }

    /*private fun findPresenterClass(): Class<P> {
        var thisClass: Class<*> = this.javaClass
        while (true) {
            (thisClass.genericSuperclass as? ParameterizedType)?.actualTypeArguments?.firstOrNull()
                ?.let {

                    return it as Class<P>
                }
                ?: run {

                    thisClass = thisClass.superclass ?: throw IllegalArgumentException()
                }
        }
    }*/

    /*
    * 找寻Presenter
    * */
    private fun <T> findPresenterClass(): T? {
        try {

            return ((this.javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[1] as Class<T>).newInstance()
        } catch (e: InstantiationException) {

            e.printStackTrace()
        } catch (e: IllegalAccessException) {

            e.printStackTrace()
        } catch (e: ClassCastException) {

            e.printStackTrace()
        }

        return null
    }

}