package com.shunplus.navigation.network;

import android.text.TextUtils;

import java.io.IOException;
import java.util.List;

import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * @author xushun on  2020/5/14 11:20.
 * Email：shunplus@163.com
 * Des：
 */
public class ResponseInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Response response = chain.proceed(chain.request());
        List<String> cookis = response.headers("Set-Cookie");
        if (cookis != null && cookis.size() > 0) {
            String s1 = cookis.get(0);
            if (!TextUtils.isEmpty(s1)) {
                String cookie = s1.split(";")[0];
//                Law.getPreference().saveString(Constants.COOKIE, cookie);//保存cookie
            }
        }
        return response;
    }
}
