package com.shunplus.navigation.utils;

import androidx.lifecycle.MutableLiveData;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xushun on  2020/5/11 21:56.
 * Email：shunplus@163.com
 * Des：基于LiveData的事件总线，更加便捷高效，替代EventBus
 *  优点：
 * 无需引入依赖，使用官方提供的LiveData类
 * 自动感应组件的生命周期，无内存泄漏风险
 * 代码量少，能完全替代EventBus
 *
 * LiveDataBus  在注册接受前发送的 消息任然可接收到
 */
public class LiveDataBus {
    private Map<String, MutableLiveData<Object>> bus;

    private LiveDataBus(){
        if (bus==null){
            bus=new HashMap<>();
        }
    }

    public static LiveDataBus get(){
        return  LiveDataBusHolder.DEFAULT_BUS;
    }

    public synchronized <T> MutableLiveData<T> with(String key,Class<T> type){
        if (!bus.containsKey(key)){
            bus.put(key,new MutableLiveData<Object>());
        }
        return (MutableLiveData<T>)bus.get(key);
    }

    public  MutableLiveData<Object> with(String key){
        return with(key,Object.class);
    }


    private static class LiveDataBusHolder{
        private static final LiveDataBus DEFAULT_BUS = new LiveDataBus();
    }
}
