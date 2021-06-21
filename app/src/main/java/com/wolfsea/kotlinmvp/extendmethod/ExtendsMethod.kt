package com.wolfsea.kotlinmvp.extendmethod
import android.app.Activity
import android.app.Service
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.*
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityOptionsCompat
import androidx.core.content.ContextCompat

/**
 *@desc 常用扩展方法
 *@author liuliheng
 *@time 2021/6/14  23:01
 **/

/**
 * 获取屏幕宽(px)
 *
 * */
val Context.screenWidthPx: Int
    get() = resources.displayMetrics.widthPixels

/**
 * 获取屏幕高(px)
 *
 * */
val Context.screenHeightPx: Int
    get() = resources.displayMetrics.heightPixels

/**
* 获取颜色
* */
fun Context.getColorCompat(color: Int) = ContextCompat.getColor(this, color)

/**
 * 获取Drawable
 * */
fun Context.getCompatDrawable(@DrawableRes id: Int) = ContextCompat.getDrawable(this, id)

/**
 * 获取Integer
 * */
fun Context.getInteger(@IntegerRes id: Int) = resources.getInteger(id)

/**
 * 获取Boolean
 *
 * */
fun Context.getBoolean(@BoolRes id: Int) = resources.getBoolean(id)

/**
 * inflater
 *
 * */
val Context.inflater: LayoutInflater
    get() = LayoutInflater.from(this)

fun Context.inflaterLayout(
    @LayoutRes layoutId: Int,
    parent: ViewGroup? = null,
    attachToRoot: Boolean = false
) = inflater.inflate(layoutId, parent, attachToRoot)


/**
 * startActivity
 *
 * */
inline fun <reified T : Activity> Context?.startActivity() =
    this?.startActivity(Intent(this, T::class.java))

inline fun <reified T : Activity> Context.startActivityWithAnimation(
    enterResId: Int = 0,
    exitResId: Int = 0
) {
    val intent = Intent(this, T::class.java)
    val bundle = ActivityOptionsCompat.makeCustomAnimation(this, enterResId, exitResId).toBundle()
    ContextCompat.startActivity(this, intent, bundle)
}

inline fun <reified T : Activity> Context.startActivityWithAnimation(
    enterResId: Int = 0,
    exitResId: Int = 0,
    intentBody: Intent.() -> Unit
) {
    val intent = Intent(this, T::class.java)
    intent.intentBody()
    val bundle = ActivityOptionsCompat.makeCustomAnimation(this, enterResId, exitResId).toBundle()
    ContextCompat.startActivity(this, intent, bundle)
}

/**
 * startService
 *
 * */
inline fun <reified T : Service> Context?.startService() =
    this?.startService(Intent(this, T::class.java))

/**
 * toast
 *
 * */
fun Context?.toast(text: CharSequence, duration: Int = Toast.LENGTH_SHORT) = this?.let {
    Toast.makeText(it, text, duration).show()
}

fun Context?.toast(@StringRes id: Int, duration: Int = Toast.LENGTH_LONG) = this?.let {
    Toast.makeText(it, id, duration).show()
}

/**
 * 点击
 *
 * */
fun <T : View> T.click(block: (T) -> Unit) = setOnClickListener { block(it as T) }

fun <T : View> T.longClick(block: (T) -> Boolean) = setOnLongClickListener { block(it as T) }

fun SingleClickListener.setViews(vararg views: View) {
    for (view in views) {
        view.setOnClickListener(this)
    }
}