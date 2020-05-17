package com.shunplus.navigation.ui.test;

import android.annotation.SuppressLint;

import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.shunplus.navigation.BR;
import com.shunplus.navigation.R;
import com.shunplus.navigation.base.BaseFragmentM;
import com.shunplus.navigation.bean.NewCourtBean;
import com.shunplus.navigation.databinding.MvvmTestBinding;
import com.shunplus.navigation.network.TransformerScheduler;
import com.shunplus.navigation.utils.PLog;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

/**
 * @author xushun on  2020/5/13 14:32.
 * Email：shunplus@163.com
 * Des：
 */
public class TestMVVMFragment extends BaseFragmentM<MvvmTestBinding, TestViewModle> {
    private List<NewCourtBean.DataBean> list;
    private TestAdapter adapter;

    @Override
    public int setLayout() {
        return R.layout.mvvm_test;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public TestViewModle initViewModel() {
        return createViewModel(this, TestViewModle.class);
    }


    @Override
    public void initParam() {

    }


    @SuppressLint("CheckResult")
    @Override
    public void initData() {
        setTitleText("MVVM");
        list = new ArrayList<>();
        adapter = new TestAdapter(R.layout.item_test, list);
        binding.recyclerview.setLayoutManager(new LinearLayoutManager(mContext));
        binding.recyclerview.setAdapter(adapter);
    }

    @Override
    public void initViewObservable() {
        viewModel.getData(this).observe(this, new Observer<List<NewCourtBean.DataBean>>() {
            @Override
            public void onChanged(List<NewCourtBean.DataBean> dataBeans) {
                list.addAll(dataBeans);
                adapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();

        // 测试 使用 lifecycle 停止 解决内存泄漏等问题
        Observable.interval(1, TimeUnit.SECONDS)
                .compose(TransformerScheduler.transform(this))
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long num) throws Exception {
                        PLog.d("onStart, running num : " + num);
                    }
                });
    }

    @Override
    public void onStop() {
        super.onStop();
        PLog.d("onStop, running num : ");
    }
}
