package com.bingogo.interceptorchain.interceptor;

import com.bingogo.interceptorchain.Interceptor;
import com.bingogo.interceptorchain.Request;
import com.bingogo.interceptorchain.Response;

import java.io.IOException;

/**
 * Created by bingo on 19/04/19.
 */
public class AInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        //在调用下一个请求前，可以更改request的信息
        //从而可以控制请求头，链接，传递的参数等

        //在这里我们将 url前面加上https://
        request.setUrl("https://"+request.getUrl());

        System.out.println("AInterceptor process, before request...");

        //调用了下一个拦截器
        Response proceed = chain.proceed(request);

        System.out.println("AInterceptor process, after request...");

        //还可以对结果进行加工
        //加工完成在返回
        // proceed.setUrl();

        return proceed;
    }
}
