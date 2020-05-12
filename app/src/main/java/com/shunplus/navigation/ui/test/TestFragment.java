package com.shunplus.navigation.ui.test;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.shunplus.navigation.R;
import com.shunplus.navigation.base.BaseToolBarFragment;

/**
 * @author xushun on  2020/5/10 14:18.
 * Email：shunplus@163.com
 * Des：
 */
public class TestFragment extends BaseToolBarFragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }



    @Override
    protected Object setLayout() {
        return R.layout.fragment_test_one;
    }

    @Override
    protected void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
