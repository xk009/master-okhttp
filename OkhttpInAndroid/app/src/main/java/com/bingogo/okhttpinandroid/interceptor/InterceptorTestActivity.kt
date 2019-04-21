package com.bingogo.okhttpinandroid.interceptor

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity

import com.bingogo.okhttpinandroid.R
import kotlinx.android.synthetic.main.activity_test_interceptor.*
import okhttp3.*
import java.io.IOException

/**
 * 拦截器使用
 *
 */
class InterceptorTestActivity : AppCompatActivity() {

    private val okHttpClient by lazy {
        OkHttpClient.Builder()
            .build()
    }

    private val request by lazy {
        Request.Builder()
            .url("http://www.publicobject.com/helloworld.txt")
            .build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_test_interceptor)

        initListener()
    }

    private fun initListener() {
        btn_application_interceptor.setOnClickListener {
            performRequestWithSpecialInterceptor {
                addInterceptor(LoggingInterceptor())
            }
        }

        btn_network_interceptor.setOnClickListener {
            performRequestWithSpecialInterceptor {
                addNetworkInterceptor(LoggingInterceptor())
            }
        }
    }

    private fun performRequestWithSpecialInterceptor(block: OkHttpClient.Builder.() -> Unit) {
        okHttpClient.newBuilder()
            .apply(block)
            .build()
            .newCall(request)
            .enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {

                }

                override fun onResponse(call: Call, response: Response) {

                }

            })
    }

    companion object {
        fun launch(context: Context) {
            Intent(context, InterceptorTestActivity::class.java).let {
                context.startActivity(it)
            }
        }
    }
}