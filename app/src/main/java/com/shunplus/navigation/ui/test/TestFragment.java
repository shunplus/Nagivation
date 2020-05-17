package com.shunplus.navigation.ui.test;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.Navigation;

import com.shunplus.navigation.R;
import com.shunplus.navigation.base.BaseToolBarFragment;
import com.shunplus.navigation.databinding.FragmentTestOneBinding;
import com.shunplus.navigation.network.TransformerScheduler;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

/**
 * @author xushun on  2020/5/10 14:18.
 * Email：shunplus@163.com
 * Des：
 */
public class TestFragment extends BaseToolBarFragment {

    FragmentTestOneBinding binding;
    UserBean userBean;
    int i = 0;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_test_one, container, false);
        userBean = new UserBean("ssss", "111");
        binding.setUser(userBean);
        binding.tvClicke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i++;
                userBean.setName(userBean.getName() + i);
                Navigation.findNavController(v).navigate(R.id.action_test_mmvvm);
            }
        });
        return binding.getRoot();
    }

    @Override
    protected Object setLayout() {
        return R.layout.fragment_test_one;
    }

    @Override
    protected void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }

    @Override
    public void initParam() {

    }

    @Override
    public void initData() {
        Observable.interval(1, TimeUnit.SECONDS)
                .compose(TransformerScheduler.transform(this))
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long num) throws Exception {
                        Log.d("Hobby", "onStart, running num : " + num);
                    }
                });
    }

    @Override
    public void initViewObservable() {

    }
}
