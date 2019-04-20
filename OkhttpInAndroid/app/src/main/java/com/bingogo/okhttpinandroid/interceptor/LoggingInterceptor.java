package com.bingogo.okhttpinandroid.interceptor;

import android.util.Log;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

class LoggingInterceptor implements Interceptor {
    private static final String TAG = "TAG";

    //当网络请求时就会调用该方法
    @Override
    public Response intercept(Interceptor.Chain chain) throws IOException {
        //获取到原来的request，注意这是还没真正的请求服务端
        Request request = chain.request();
        //获取当前时间，并打印日志说要发起请求了
        //同时使用了前面我们也讲到了的headers打印请求头
        long t1 = System.nanoTime();
        //这里打印可以使用自己的日志框架
        Log.d(TAG, String.format("Sending request %s on %s%n%s",
                request.url(), chain.connection(), request.headers()));
        Response response = chain.proceed(request);
        long t2 = System.nanoTime();
        //还是根据是否是调试模式打印不同信息
        Log.d(TAG, String.format("Received response for %s in %.1fms%n%s",
                response.request().url(), (t2 - t1) / 1e6d, response.headers()));
        return response;
    }
}
