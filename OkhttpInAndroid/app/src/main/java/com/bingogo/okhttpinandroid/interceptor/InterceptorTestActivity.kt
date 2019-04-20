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
//                - 不用担心请求过程中调用了几次
//                - 重定向，重试，从缓存中取数据都也只会调用一次
//                - 不关心Okhttp注入的请求头，如If-None-Match
//                - 允许请求短路，也就是说可以不调用Chain.proceed()来终止调用
//                - 允许重试也就是调用多次Chain.proceed()方法
                addInterceptor(LoggingInterceptor())
            }
        }

        btn_network_interceptor.setOnClickListener {
            performRequestWithSpecialInterceptor {
//                - 能够操作请求过程，比如：重定向，重试
//                - 当返回缓存数据时不调用
//                - 用来只观察网络上传输的数据
//                - 同时看到请求的链接，包括重定向的链接
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