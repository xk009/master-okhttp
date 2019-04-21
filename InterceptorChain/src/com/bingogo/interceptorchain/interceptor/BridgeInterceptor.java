package com.bingogo.interceptorchain.interceptor;

import com.bingogo.interceptorchain.Interceptor;
import com.bingogo.interceptorchain.Request;
import com.bingogo.interceptorchain.Response;

import java.io.IOException;

/**
 * Created by bingo on 19/04/21.
 */
public class BridgeInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {

        Request request = chain.request();

        // 前置添加各种缺失的header
        System.out.println("BridgeInterceptor process, before request...");
        System.out.println("前置添加各种缺失的header");

        Response response = chain.proceed(request);

        System.out.println("需要的话，帮我们解析 gzip压缩");

        System.out.println("BridgeInterceptor process, after request..." );



        return response;
    }
}
