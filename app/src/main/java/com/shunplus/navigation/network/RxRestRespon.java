package com.shunplus.navigation.network;

import android.net.ParseException;
import android.util.Log;

import com.google.gson.JsonParseException;
import com.shunplus.navigation.network.exception.ApiException;
import com.shunplus.navigation.network.response.ResponseWrapper;

import org.json.JSONException;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import retrofit2.HttpException;

/**
 * @author xushun on  2020/5/15 10:52.
 * Email：shunplus@163.com
 * Des：
 */
public abstract class RxRestRespon<T> implements Observer<ResponseWrapper<T>> {

    public abstract void onSucess(T data);


    public abstract void onFailure(ApiException error);


    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(ResponseWrapper<T> data) {
        Log.d("Hobby", " RxRestRespon onNext");
        if (data.isIserror()) {
            ApiException apiException = new ApiException();
            apiException.errorCode = ApiException.SERVER_ERROR;
            apiException.errorMessage = data.getMessage();
            onFailure(apiException);
        } else {
            if (data.getData() == null) {
                ApiException apiException = new ApiException();
                apiException.errorCode = ApiException.SERVER_ERROR;
                apiException.errorMessage = data.getMessage();
                onFailure(apiException);
            } else {
                onSucess(data.getData());
            }

        }
    }


    @Override
    public void onError(Throwable e) {
        Log.d("Hobby", " Re" + Log.getStackTraceString(e));
        ApiException apiException = new ApiException();
        if (e instanceof HttpException) {
            int code = ((HttpException) e).code();
            apiException.errorCode = code;
        } else if (e instanceof UnknownHostException) {
            apiException.errorCode = ApiException.UNKNOWN_ERROR;
        } else if (e instanceof ConnectException) {
            apiException.errorCode = ApiException.NETWORK_NOT_CONNECT;
        } else if (e instanceof SocketTimeoutException) {
            apiException.errorCode = ApiException.CONNECT_TIMEOUT;
        } else if (e instanceof JsonParseException
                || e instanceof JSONException
                || e instanceof ParseException) {
            apiException.errorCode = ApiException.PARSE_ERROR;
        } else {
            apiException.errorCode = ApiException.UNKNOWN_ERROR;
        }
        onFailure(apiException);

    }

    @Override
    public void onComplete() {

    }
}
