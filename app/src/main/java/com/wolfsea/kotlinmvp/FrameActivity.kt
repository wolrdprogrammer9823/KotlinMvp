package com.wolfsea.kotlinmvp
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.wolfsea.kotlinmvp.coroutinecase.CoroutineCaseActivity
import com.wolfsea.kotlinmvp.extendmethod.startActivity
import kotlinx.android.synthetic.main.activity_frame.*

class FrameActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_frame)

        initEvent()
    }

    private fun initEvent() {

        to_main.setOnClickListener {
            startActivity<MainActivity>()
        }

        to_coroutine.setOnClickListener {
            startActivity<CoroutineCaseActivity>()
        }
    }
}