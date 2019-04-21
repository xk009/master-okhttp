package com.bingogo.interceptorchain.interceptor;


import com.bingogo.interceptorchain.Interceptor;
import com.bingogo.interceptorchain.Request;
import com.bingogo.interceptorchain.Response;

import java.io.IOException;

/**
 * Created by bingo on 19/04/19.
 */
public class CallServerInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();

        System.out.println("CallServerInterceptor process, before request...");

        //最后一个拦截器，不调用chain.proceed的，不然没法结束
//        Response proceed = chain.proceed(request);

        //创建结果，在真实的okhttp源码中这个拦截器会从网络请求数据
        //我们这里只是模拟这个过程
        Response response = new Response(request.getUrl());
        System.out.println("基于网络IO创建Response");

        System.out.println("CallServerInterceptor process, after request...");

        //再对结果处理

        return response;
    }
}
