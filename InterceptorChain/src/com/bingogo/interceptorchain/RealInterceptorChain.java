/*
 * Copyright (C) 2016 Square, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.bingogo.interceptorchain;


import java.io.IOException;
import java.util.List;

/**
 * A concrete interceptor chain that carries the entire interceptor chain: all application
 * interceptors, the OkHttp core, all network interceptors, and finally the network caller.
 */
public final class RealInterceptorChain implements Interceptor.Chain {
  private final List<Interceptor> interceptors;
  private final int index;
  private final Request request;

  public RealInterceptorChain(List<Interceptor> interceptors,int index,Request request) {
    this.interceptors = interceptors;
    this.index = index;
    this.request = request;
  }



  @Override public Request request() {
    return request;
  }

  @Override public Response proceed(Request request) throws IOException {
//    if (index >= interceptors.size()) throw new AssertionError();

    // Call the next interceptor in the chain.
    RealInterceptorChain next = new RealInterceptorChain(interceptors,index + 1, request);
    Interceptor interceptor = interceptors.get(index);

    Response response = interceptor.intercept(next);


    return response;
  }

}
