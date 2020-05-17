package com.shunplus.navigation.network.response;

import com.shunplus.navigation.network.exception.ApiException;
import com.shunplus.navigation.network.exception.CustomException;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.functions.Function;

/**
 * des:
 *
 * @author xushun
 * @time 2020/5/17 13:19
 */

public class ResponseTransformer {

    public static <T> ObservableTransformer<ResponseWrapper<T>, T> handleResult() {
        return (Observable<ResponseWrapper<T>> upstream) -> {
            return upstream
                    .onErrorResumeNext(new ErrorResumeFunction<>())
                    .flatMap(new ResponseFunction<>());
        };
    }


    /**
     * 非服务器产生的异常，比如本地无无网络请求，Json数据解析错误等等。
     *
     * @param <T>
     */
    private static class ErrorResumeFunction<T> implements Function<Throwable, ObservableSource<? extends ResponseWrapper<T>>> {

        @Override
        public ObservableSource<? extends ResponseWrapper<T>> apply(Throwable throwable) throws Exception {
            return Observable.error(CustomException.handleException(throwable));
        }
    }

    /**
     * 服务其返回的数据解析
     * 正常服务器返回数据和服务器可能返回的exception
     *
     * @param <T>
     */
    private static class ResponseFunction<T> implements Function<ResponseWrapper<T>, ObservableSource<T>> {

        @Override
        public ObservableSource<T> apply(ResponseWrapper<T> tResponse) throws Exception {
            int code = 0 /*tResponse.getErrorCode()*/;
            String message = tResponse.getMessage();
            if (code == 200) {
                return Observable.just(tResponse.getData());
            } else {
                return Observable.error(new ApiException(code, message));
            }
        }
    }


}
