package com.shunplus.navigation.ui.test;

import android.util.Log;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.OnLifecycleEvent;

/**
 * @author xushun on  2020/5/11 17:07.
 * Email：shunplus@163.com
 * Des：
 */
public class LiftCycleTest implements LifecycleObserver {

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    public void  onCreate(LifecycleOwner owner){
        Log.d("Lifecycle"," onCreate ");
    }


    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void  onReseume(LifecycleOwner owner){
        Log.d("Lifecycle"," onReseume ");
    }
}
