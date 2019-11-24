package com.basics.http.interceptor;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

public class NetworkInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {

        return null;
    }
}
