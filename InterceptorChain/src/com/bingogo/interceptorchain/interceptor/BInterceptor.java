package com.bingogo.interceptorchain.interceptor;

import com.bingogo.interceptorchain.Interceptor;
import com.bingogo.interceptorchain.Request;
import com.bingogo.interceptorchain.Response;

import java.io.IOException;

/**
 * Created by bingo on 19/04/19.
 */
public class BInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();

        System.out.println("BInterceptor process, before request...");

        Response proceed = chain.proceed(request);

        System.out.println("BInterceptor process, after request...");

        return proceed;
    }
}
