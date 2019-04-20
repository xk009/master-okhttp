package com.bingogo.okhttpinandroid.proxy

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.bingogo.okhttpinandroid.R
import com.bingogo.okhttpinandroid.utils.log
import kotlinx.android.synthetic.main.activity_proxy.*
import okhttp3.*
import java.io.IOException
import java.net.InetSocketAddress
import java.net.Proxy

class ProxyActivity : AppCompatActivity() {

    private val okHttpClientWithNoProxy by lazy {
        OkHttpClient.Builder()
            .proxy(Proxy.NO_PROXY) // 强制不走代理，可以防止抓包
            .build()
    }

    private val okHttpClientWithSpecialProxy by lazy {
        // 指定http代理，可以不再手机全局设置，强行指定代理
        OkHttpClient.Builder()
            .proxy(Proxy(Proxy.Type.HTTP, InetSocketAddress("192.168.1.32", 8888)))
            .build()
    }

    private val request by lazy {
        Request.Builder()
            .url("http://47.98.118.255")
            .build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_proxy)

        initListener()

    }

    private fun initListener() {
        btn_disable_proxy.setOnClickListener {
            okHttpClientWithNoProxy.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    e.log()
                }

                override fun onResponse(call: Call, response: Response) {
                    response.log()
                }

            })
        }

        btn_set_proxy.setOnClickListener {
            okHttpClientWithSpecialProxy.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    e.log()
                }

                override fun onResponse(call: Call, response: Response) {
                    response.log()
                }

            })
        }
    }

    companion object {
        fun launch(context: Context) {
            Intent(context, ProxyActivity::class.java).let {
                context.startActivity(it)
            }
        }
    }

}