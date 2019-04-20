package com.bingogo.okhttpinandroid

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.bingogo.okhttpinandroid.cache.CacheActivity
import com.bingogo.okhttpinandroid.interceptor.InterceptorTestActivity
import com.bingogo.okhttpinandroid.proxy.ProxyActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_test_cache.setOnClickListener {
            CacheActivity.launch(this)
        }

        btn_test_interceptor.setOnClickListener {
            InterceptorTestActivity.launch(this)
        }

        btn_test_proxy.setOnClickListener {
            ProxyActivity.launch(this)
        }
    }
}
