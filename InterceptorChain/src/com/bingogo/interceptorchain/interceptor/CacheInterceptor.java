package com.bingogo.interceptorchain.interceptor;

import com.bingogo.interceptorchain.Interceptor;
import com.bingogo.interceptorchain.Request;
import com.bingogo.interceptorchain.Response;

import java.io.IOException;

/**
 * Created by bingo on 19/04/21.
 */
public class CacheInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();

        // 检查如果有缓存的话，直接返回缓存，不需要调用 下一个拦截器
        System.out.println("CacheInterceptor process, before request...");

        System.out.println("检查如果有缓存的话，直接返回缓存，不需要调用 下一个拦截器");

        Response response = chain.proceed(request);

        System.out.println("CacheInterceptor process, after request...");

        return response;
    }
}
