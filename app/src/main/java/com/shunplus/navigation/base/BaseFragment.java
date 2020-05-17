package com.shunplus.navigation.base;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.trello.rxlifecycle2.components.support.RxFragment;

/**
 * @author xushun on  2020/5/12 09:34.
 * Email：shunplus@163.com
 * Des：
 */
public abstract class BaseFragment extends RxFragment implements BaseView {

    protected BaseActivity mActivity;
    private View rootView;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (BaseActivity) getActivity();
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (setLayout() instanceof Integer) {
            rootView = inflater.inflate((Integer) setLayout(), container, false);
        } else if (setLayout() instanceof View) {
            rootView = (View) setLayout();
        } else {
            throw new ClassCastException("BaseFragment setLayout() type must be int or setView");
        }
        onBindView(savedInstanceState, rootView);
        return rootView;
    }

    /**
     * 布局
     *
     * @return object
     */
    protected abstract Object setLayout();

    /**
     * 获取设置的布局
     *
     * @return
     */
    public View getContentView() {
        return rootView;
    }

    /**
     * setView 初始化
     *
     * @param savedInstanceState savedInstanceState
     * @param rootView           rootView
     */
    protected abstract void onBindView(@Nullable Bundle savedInstanceState, View rootView);
}
