package com.shunplus.navigation.bean;

/**
 * des:
 *
 * @author xushun
 * @time 2020/5/14 11:01
 */

public class BaseBean {
    private String errorCode;
    private boolean iserror;
    private String message;
    private String exception;

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

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
}
