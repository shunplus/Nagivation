package com.shunplus.navigation.network.exception;

import android.content.Context;
import android.widget.Toast;

import com.shunplus.navigation.R;


/**
 * des:
 *
 * @author xushun
 * @time 2020/5/17 13:19
 */
public class ApiException extends Exception {
    public final static int INTERNAL_SERVER_ERROR = 500;//服务器出错啦~~~
    public final static int BAD_GATEWAY = 502;//
    public final static int NOT_FOUND = 404;//
    public final static int CONNECT_TIMEOUT = 408;//
    public final static int NETWORK_NOT_CONNECT = 499;//
    public final static int UNKNOWN_ERROR = 700;//
    public static final int PARSE_ERROR = 1001;
    public static final int SERVER_ERROR = 1002;
    public int errorCode;
    public String errorMessage;

    public ApiException() {
        this.errorCode = 0;
        this.errorMessage = "";
    }

    public ApiException(int errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public ApiException(int errorCode) {
        this.errorCode = errorCode;
        this.errorMessage = "";
    }

    public static void handleError(Context context, ApiException error) {
        switch (error.errorCode) {
            case ApiException.INTERNAL_SERVER_ERROR:
            case ApiException.BAD_GATEWAY:
                Toast.makeText(context, context.getText(R.string.service_error), Toast.LENGTH_SHORT).show();
                break;
            case ApiException.NOT_FOUND:
                Toast.makeText(context, context.getText(R.string.not_found), Toast.LENGTH_SHORT).show();
                break;
            case ApiException.CONNECT_TIMEOUT:
                Toast.makeText(context, context.getText(R.string.timeout), Toast.LENGTH_SHORT).show();
                break;
            case ApiException.NETWORK_NOT_CONNECT:
                Toast.makeText(context, context.getText(R.string.network_error), Toast.LENGTH_SHORT).show();
                break;
            case ApiException.UNKNOWN_ERROR:
                Toast.makeText(context, context.getText(R.string.unknown_error), Toast.LENGTH_SHORT).show();
                break;
            case ApiException.PARSE_ERROR:
                Toast.makeText(context, context.getText(R.string.parse_error), Toast.LENGTH_SHORT).show();
                break;
            case ApiException.SERVER_ERROR:
                Toast.makeText(context, context.getText(R.string.parse_error), Toast.LENGTH_SHORT).show();
                break;
        }
    }

}
