package com.shunplus.navigation.base;

import com.trello.rxlifecycle2.LifecycleTransformer;

/**
 * @author xushun on  2020/5/13 11:04.
 * Email：shunplus@163.com
 * Des：
 */

public interface BaseView {

    /**
     * 网络请求 lifecycle bind
     *
     * @param <T>
     * @return
     */
    <T> LifecycleTransformer<T> bindToLifecycle();

    /**
     * 初始化界面传递参数
     * 屏幕设置等，需要在布局初始化之前的操作可再在此处理
     */
    void initParam();

    /**
     * 初始化数据
     */
    void initData();

    /**
     * 初始化界面观察者的监听
     */
    void initViewObservable();
}
