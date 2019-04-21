package com.bingogo.interceptorchain.interceptor;

import com.bingogo.interceptorchain.Interceptor;
import com.bingogo.interceptorchain.Request;
import com.bingogo.interceptorchain.Response;

import java.io.IOException;

/**
 * Created by bingo on 19/04/21.
 */
public class RetryAndFollowUpInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();

        // 各种重试 while(true)
        System.out.println("RetryAndFollowUpInterceptor process, before request...");
        System.out.println("各种重试，调用下一个Chain 来获取response");

        Response response = chain.proceed(request);

        System.out.println("RetryAndFollowUpInterceptor process, after request...");

        return response;

    }
}
