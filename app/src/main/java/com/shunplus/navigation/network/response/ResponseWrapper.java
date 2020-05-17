package com.shunplus.navigation.network.response;

/**
 * Created by Zaifeng on 2018/2/28.
 * 返回结果封装
 */

public class ResponseWrapper<T> {

    private T data;
    private String errorCode;
    private boolean iserror;
    private String message;
    private String exception;

    public boolean isIserror() {
        return iserror;
    }

    public void setIserror(boolean iserror) {
        this.iserror = iserror;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getException() {
        return exception;
    }

    public void setException(String exception) {
        this.exception = exception;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
