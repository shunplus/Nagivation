package com.shunplus.navigation.network;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author xushun on  2020/5/14 11:37.
 * Email：shunplus@163.com
 * Des：
 */
public class CommonParamsInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();
        Request.Builder requestBuilder = original.newBuilder()
                .header("Content-Type", "application/json")
                .header("Cache-Control", "no-cache")
                .header("app", "true")
                .header("Cookie", "");
        return chain.proceed(original);
    }
}
