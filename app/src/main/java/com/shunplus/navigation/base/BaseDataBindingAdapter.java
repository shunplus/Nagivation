package com.shunplus.navigation.base;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * @author xushun on  2020/5/17 12:07.
 * Email：shunplus@163.com
 * Des：
 */
public abstract class BaseDataBindingAdapter<V extends ViewDataBinding, T> extends BaseQuickAdapter<T, BaseViewHolder> {
    protected V binding;
    private int variableId;

    public BaseDataBindingAdapter(int layoutResId, @Nullable List<T> data) {
        super(layoutResId, data);
        variableId = initVariableId();
    }

    /**
     * 初始化ViewModel的id
     *
     * @return BR的id
     */
    public abstract int initVariableId();

    public abstract void bindConvert(BaseViewHolder helper, T item);

    @Override
    protected void convert(BaseViewHolder helper, T item) {
        binding = DataBindingUtil.bind(helper.itemView);
        binding.setVariable(variableId, item);
        bindConvert(helper, item);
        binding.executePendingBindings();
    }
}
