package com.bingogo.okhttpinandroid.cache;

import android.util.Log;
import com.bingogo.okhttpinandroid.utils.NetworkUtil;
import me.yifeiyuan.pandora.ApplicationUtils;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

class CustomCacheInterceptor implements Interceptor {

    private static final String TAG = "CustomCacheInterceptor";

    @Override
    public Response intercept(Interceptor.Chain chain) throws IOException {
        Request request = chain.request();
        //没有网络，强制返回本地缓存的，有可能为空
        if (!NetworkUtil.isNetworkConnected(ApplicationUtils.getApplication())) {
            request = request.newBuilder()
                    .cacheControl(CacheControl.FORCE_CACHE)
                    .build();
            Log.d(TAG, "no network");
        }
        Response originalResponse = chain.proceed(request);

        if (NetworkUtil.isNetworkConnected(ApplicationUtils.getApplication())) {
            //有网的时候读接口上的@Headers里的配置，你可以在这里进行统一的设置
            //这里也可以判断，如果不支持缓存，这里才设置缓存，比如都缓存60秒
            //还有种情况是，也可以在这里判断，如果当前用户是3G网络才缓存，wifi不缓存

            //这里设置了任何请求都缓存60秒(包括服务端不支持缓存的网址)
            CacheControl cacheControl = new CacheControl.Builder().maxAge(60, TimeUnit.SECONDS).build();
            return originalResponse.newBuilder()
                    .header("Cache-Control", cacheControl.toString())
                    .removeHeader("Pragma") //兼容1.0的头可以移除
                    .build();
        } else {
            //没网的时候，需要将有可能过期的缓存修改为，不过期的
            //不然后面不会读取缓存，因为已经过期了
            return originalResponse.newBuilder()
                    .header("Cache-Control", "public, only-if-cached, max-stale=2419200")
                    .removeHeader("Pragma") //兼容1.0的头可以移除
                    .build();
        }
    }
};
