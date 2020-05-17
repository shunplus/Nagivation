package com.shunplus.navigation.base;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

/**
 * @author xushun on  2020/5/10 20:45.
 * Email：shunplus@163.com
 * Des：
 */
public abstract class BaseActivity extends RxAppCompatActivity implements BaseView {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        init();
    }

    protected abstract int getLayoutId();
    protected abstract void init();


}
