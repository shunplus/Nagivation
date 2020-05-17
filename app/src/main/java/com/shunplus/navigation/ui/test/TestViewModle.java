package com.shunplus.navigation.ui.test;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.google.gson.Gson;
import com.shunplus.navigation.base.BaseView;
import com.shunplus.navigation.base.BaseViewModel;
import com.shunplus.navigation.bean.NewCourtBean;
import com.shunplus.navigation.network.RxRestClient;
import com.shunplus.navigation.network.RxRestRespon;
import com.shunplus.navigation.network.TransformerScheduler;
import com.shunplus.navigation.network.exception.ApiException;

import java.util.List;

/**
 * @author xushun on  2020/5/13 16:33.
 * Email：shunplus@163.com
 * Des：
 */
public class TestViewModle extends BaseViewModel {

    public TestViewModle(@NonNull Application application) {
        super(application);

    }

    public MutableLiveData<List<NewCourtBean.DataBean>> getData(BaseView baseView) {

        MutableLiveData<List<NewCourtBean.DataBean>> liveData = new MutableLiveData<>();

        RxRestClient.get().getService().getCourtList("0")
                .compose(TransformerScheduler.transform(baseView))
                .subscribe(new RxRestRespon<List<NewCourtBean.DataBean>>() {
                    @Override
                    public void onSucess(List<NewCourtBean.DataBean> data) {
                        Log.d("Hobby", new Gson().toJson(data));
                        liveData.setValue(data);
                    }

                    @Override
                    public void onFailure(ApiException error) {
                        Log.d("Hobby", error.errorCode + "");
                        liveData.setValue(null);
                    }
                });


        return liveData;
    }

}
