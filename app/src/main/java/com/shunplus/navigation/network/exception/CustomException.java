package com.shunplus.navigation.network.exception;

import android.net.ParseException;

import com.google.gson.JsonParseException;

import org.json.JSONException;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import static com.shunplus.navigation.network.exception.ApiException.CONNECT_TIMEOUT;
import static com.shunplus.navigation.network.exception.ApiException.NETWORK_NOT_CONNECT;
import static com.shunplus.navigation.network.exception.ApiException.PARSE_ERROR;
import static com.shunplus.navigation.network.exception.ApiException.UNKNOWN_ERROR;


/**
 * des:
 *
 * @author xushun
 * @time 2020/5/17 13:19
 */

public class CustomException {

    public static ApiException handleException(Throwable e) {
        ApiException ex;
        if (e instanceof JsonParseException
                || e instanceof JSONException
                || e instanceof ParseException) {
            //解析错误
            ex = new ApiException(PARSE_ERROR, e.getMessage());
            return ex;
        } else if (e instanceof ConnectException) {
            //网络错误
            ex = new ApiException(NETWORK_NOT_CONNECT, e.getMessage());
            return ex;
        } else if (e instanceof UnknownHostException || e instanceof SocketTimeoutException) {
            //连接错误
            ex = new ApiException(CONNECT_TIMEOUT, e.getMessage());
            return ex;
        } else {
            //未知错误
            ex = new ApiException(UNKNOWN_ERROR, e.getMessage());
            return ex;
        }
    }
}
