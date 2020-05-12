package com.shunplus.navigation.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.ContentFrameLayout;
import androidx.appcompat.widget.Toolbar;

import com.shunplus.navigation.utils.ToolBarHelper;

/**
 * @author xushun on  2020/5/12 09:40.
 * Email：shunplus@163.com
 * Des：
 */
public abstract class BaseToolBarFragment extends BaseFragment {

    public Toolbar mToolbar;
    private ToolBarHelper mToolBarHelper;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //指出fragment愿意添加item到选项菜单
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mToolBarHelper = new ToolBarHelper(mActivity, setLayout());
        mToolbar = mToolBarHelper.getToolBar();
        mActivity.setSupportActionBar(mToolbar);
        onCreateCustomToolBar(mToolbar);
        setToolbarListener();
        ContentFrameLayout contentView = mToolBarHelper.getContentView();
        onBindView(savedInstanceState, contentView);
        return contentView;
    }


    protected void onCreateCustomToolBar(Toolbar toolbar) {
        //插入toolbar视图的内容的起始点与结束点
        toolbar.setContentInsetsRelative(0, 0);
        //隐藏标题和子标题
        if (mActivity.getSupportActionBar() != null) {
            mActivity.getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
    }

    private void setToolbarListener() {
        mToolBarHelper.getRightView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickRight();
            }
        });

        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               getActivity().onBackPressed();
            }
        });
    }

    /**
     * 右边的标题的监听事件
     */
    public void onClickRight() {

    }

    /**
     * 隐藏返回按钮
     */
    public void hideBack() {
        mActivity.getSupportActionBar().setDisplayHomeAsUpEnabled(false);
    }

    /**
     * 设置标题
     *
     * @param title 标题
     */
    public void setTitleText(String title) {
        mToolBarHelper.setTitleText(title);
    }

    /**
     * 设置右边的标题文字
     *
     * @param title 标题
     */
    public void setRightTitle(String title) {
        mToolBarHelper.setRightText(title);
    }

    /**
     * 获取右边的view
     */
    public TextView getRightView() {
        return mToolBarHelper.getRightView();
    }

    /**
     * 获取中间视图
     */
    public TextView getTitleView() {
        return mToolBarHelper.getTitleView();
    }
}
