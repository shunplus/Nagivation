package com.shunplus.navigation.ui.test;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseViewHolder;
import com.shunplus.navigation.BR;
import com.shunplus.navigation.base.BaseDataBindingAdapter;
import com.shunplus.navigation.bean.NewCourtBean;
import com.shunplus.navigation.databinding.ItemTestBinding;

import java.util.List;

/**
 * @author xushun on  2020/5/16 12:32.
 * Email：shunplus@163.com
 * Des：
 */
public class TestAdapter extends BaseDataBindingAdapter<ItemTestBinding, NewCourtBean.DataBean> {
    public TestAdapter(int layoutResId, @Nullable List<NewCourtBean.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    public int initVariableId() {
        return BR.item;
    }

    @Override
    public void bindConvert(BaseViewHolder helper, NewCourtBean.DataBean item) {
    }
}
