package com.bingogo.interceptorchain;

import com.bingogo.interceptorchain.interceptor.*;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by bingo on 19/04/19.
 */
public class TestInterceptorChain {
    @Test
    public void testInterceptorChain(){
        try {
            Response response = getResponseWithInterceptorChain();
            System.out.println(response.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Response getResponseWithInterceptorChain() throws IOException {
        Request originalRequest = new Request("www.bingo.com");

        // Build a full stack of interceptors.
        List<Interceptor> interceptors = new ArrayList<>();
//        interceptors.add(new AInterceptor());
//        interceptors.add(new BInterceptor());
        interceptors.add(new RetryAndFollowUpInterceptor());
        interceptors.add(new BridgeInterceptor());
        interceptors.add(new CacheInterceptor());
        interceptors.add(new ConnectInterceptor());
        interceptors.add(new CallServerInterceptor());

        Interceptor.Chain chain = new RealInterceptorChain(
                interceptors, 0, originalRequest);

        return chain.proceed(originalRequest);
    }
}
