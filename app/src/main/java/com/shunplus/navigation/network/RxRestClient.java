package com.shunplus.navigation.network;

import android.util.Log;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.shunplus.navigation.BuildConfig;
import com.shunplus.navigation.app.RxRestServiceApi;

import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author xushun on  2020/5/14 11:09.
 * Email：shunplus@163.com
 * Des：
 */
public class RxRestClient {

    private OkHttpClient okHttpClient;
    private RxRestServiceApi apiService;
    private Retrofit retrofit;

    private RxRestClient() {
    }

    public static RxRestClient get() {
        return RxRestClientHolder.instance;
    }

    private static SSLSocketFactory createSSLSocketFactory() {
        SSLSocketFactory ssfFactory = null;

        try {
            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(null, new TrustManager[]{new TrustAllCerts()}, new SecureRandom());

            ssfFactory = sc.getSocketFactory();
        } catch (Exception e) {
        }

        return ssfFactory;
    }

    private void creatRetrofit() {
        okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(30000, TimeUnit.MILLISECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .addInterceptor(new ResponseInterceptor())
                .addInterceptor(new CommonParamsInterceptor())
                .addInterceptor(new HttpLoggingInterceptor(new HttpLogger()).setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE))
                //信任所有证书
                .hostnameVerifier(new TrustAllHostnameVerifier())
                .sslSocketFactory(createSSLSocketFactory())
                .build();
        retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl("https://gistest.shgbitcloud.com/")
                .build();
        apiService = retrofit.create(RxRestServiceApi.class);
    }

    public <T> T getService(final Class<T> service) {
        if (retrofit == null) {
            creatRetrofit();
        }
        return retrofit.create(service);
    }

    public RxRestServiceApi getService() {
        if (apiService == null) {
            creatRetrofit();
        }
        return apiService;
    }


    private static class RxRestClientHolder {
        private static RxRestClient instance = new RxRestClient();
    }

    public static class TrustAllCerts implements X509TrustManager {
        @Override
        public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        }

        @Override
        public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];
        }
    }

    public class TrustAllHostnameVerifier implements HostnameVerifier {
        @Override
        public boolean verify(String hostname, SSLSession session) {
            return true;
        }
    }


    public class HttpLogger implements HttpLoggingInterceptor.Logger {
        private StringBuilder mMessage = new StringBuilder();

        @Override
        public void log(String message) {
            if (message.startsWith("--> POST")) {
                mMessage.setLength(0);
            }
            // 以{}或者[]形式的说明是响应结果的json数据，需要进行格式化
//            val isJson = (message.startsWith("{") && message.endsWith("}")
//                    || message.startsWith("[") && message.endsWith("]"))
//            if (isJson) {
//                message = JsonUtil.formatJson(message)
//            }
            mMessage.append(message + "\n");
            // 请求或者响应结束，打印整条日志
            if (message.startsWith("<-- END HTTP")) {
                Log.i("OKHTTP", mMessage.toString());
            }
        }
    }


}
