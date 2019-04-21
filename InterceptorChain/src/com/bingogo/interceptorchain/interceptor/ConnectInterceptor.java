package com.bingogo.interceptorchain.interceptor;

import com.bingogo.interceptorchain.Interceptor;
import com.bingogo.interceptorchain.Request;
import com.bingogo.interceptorchain.Response;

import java.io.IOException;

/**
 * Created by bingo on 19/04/21.
 */
public class ConnectInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();


        System.out.println("ConnectInterceptor process, before request...");

        System.out.println("建立tcp连接");

        Response response = chain.proceed(request);

        System.out.println("ConnectInterceptor process, after request...");

        return response;
    }
}
