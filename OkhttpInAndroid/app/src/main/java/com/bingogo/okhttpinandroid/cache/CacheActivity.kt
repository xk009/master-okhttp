package com.bingogo.okhttpinandroid.cache

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.bingogo.okhttpinandroid.R
import kotlinx.android.synthetic.main.activity_cache.*
import okhttp3.*
import java.io.File
import java.io.IOException
import kotlin.concurrent.thread


class CacheActivity : AppCompatActivity() {

    private val okHttpClient by lazy {
        OkHttpClient.Builder()
            .cache(cache)
            .addInterceptor(CustomCacheInterceptor())
            .addNetworkInterceptor(CustomCacheInterceptor())
            .build()
    }

    private val request by lazy {
        Request.Builder()
            .url("http://47.98.118.255")
            .build()
    }

    private val cache by lazy {
        Cache(File(cacheDir, "http"), 1024 * 1024 * 100)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cache)

        btn_normal.setOnClickListener {
            thread {
                requestTwoTimes(request)
            }
        }

        btn_force_cache.setOnClickListener {
            thread {
                requestTwoTimes(request.newBuilder()
                    .cacheControl(CacheControl.FORCE_CACHE)
                    .build()
                )
            }
        }

        btn_force_network.setOnClickListener {
            thread {
                requestTwoTimes(request.newBuilder()
                    .cacheControl(CacheControl.FORCE_NETWORK)
                    .build()
                )
            }
        }
    }

    private fun requestTwoTimes(request: Request) {

        try {
            var response = okHttpClient.newCall(request).execute()
            if (response.isSuccessful) {
                logResponse(response)

            } else {
                Log.d(TAG, "error1: " + response.code())
            }

            //第二次请求
            response = okHttpClient.newCall(request).execute()
            if (response.isSuccessful) {
                logResponse(response)
            } else {
                Log.d(TAG, "error2: " + response.code())
            }

        } catch (e: Exception) {

        }


    }

    private fun logResponse(response: Response) {

        //使用了缓存，他返回null
        Log.d(TAG, "networkResponse: " + response.networkResponse())

        //如果没有缓存，或者不适用缓存，缓存过期返回null
        Log.d(TAG, "cacheResponse: " + response.cacheResponse())

        Log.d(TAG, "response: " + bodyString(response))
        //缓存的response
    }

    @Throws(IOException::class)
    private fun bodyString(response: Response?): String? {
        return response?.body()?.string()
    }

    companion object {
        const val TAG = "CacheActivity"

        fun launch(context: Context) {
            Intent(context, CacheActivity::class.java).let {
                context.startActivity(it)
            }
        }
    }
}