package com.shunplus.navigation.app;

import com.shunplus.navigation.bean.NewCourtBean;
import com.shunplus.navigation.network.response.ResponseWrapper;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * @author xushun on  2020/5/14 10:53.
 * Email：shunplus@163.com
 * Des：请求接口api
 */
public interface RxRestServiceApi {


    /**
     * 获取法院列表
     */
    @POST("/iexe/a/sys/office/getCourtList")
    @FormUrlEncoded
    Observable<ResponseWrapper<List<NewCourtBean.DataBean>>> getCourtList(@Field("id") String id);
}
