package com.shunplus.navigation.network;

import com.shunplus.navigation.base.BaseView;

import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * @author xushun on  2020/5/15 21:59.
 * Email：shunplus@163.com
 * Des：
 */
public class TransformerScheduler {

    /**
     * 界面请求，不需要加载和隐藏loading时调用 使用RxLifeCycle
     * 传入view接口，Activity，Fragment等实现了view接口，Activity，Fragment继承于{@link com.trello.rxlifecycle2.components.support.RxAppCompatActivity/RxFragment}
     * 也就实现了bindToLifecycle方法
     * 处理rxjava泄漏问题
     *
     * @param view View
     * @param <T>  泛型
     * @return
     */
    public static <T> ObservableTransformer<T, T> transform(final BaseView view) {
        return observable -> observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(view.bindToLifecycle());
    }

}
