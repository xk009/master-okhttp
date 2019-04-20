package com.bingogo.okhttpinandroid.utils

import android.util.Log
import okhttp3.Response


fun Response.log() {
    Log.d("Response", body()?.string() ?: "empty content")
}

fun Exception.log() {
    Log.e("ResponseError", this.localizedMessage)
}