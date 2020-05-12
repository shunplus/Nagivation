package com.shunplus.navigation.ui.test;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;

import com.shunplus.navigation.MsgEvent;
import com.shunplus.navigation.R;
import com.shunplus.navigation.base.BaseActivity;
import com.shunplus.navigation.utils.LiveDataBusX;

/**
 * @author xushun on  2020/5/10 15:25.
 * Email：shunplus@163.com
 * Des：
 */
public class TestActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.test_activity_layout;
    }

    @Override
    protected void init() {
//        setToolBarTitle("测试");

        //注册订阅事件
        LiveDataBusX.get().with("data", MsgEvent.class).observe(this, new Observer<MsgEvent>() {
            @Override
            public void onChanged(MsgEvent msgEvent) {
                Toast.makeText(TestActivity.this, msgEvent.getPosition()+"", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
