package com.shunplus.navigation.utils;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.appcompat.widget.ContentFrameLayout;
import androidx.appcompat.widget.Toolbar;

import com.shunplus.navigation.R;

/**
 * @author xushun on  2020/5/11 10:26.
 * Email：shunplus@163.com
 * Des：
 */

public class ToolBarHelper {
    /**
     * 两个属性
     * 1，toolbar是否悬浮在窗口之上
     * 2，toolbar的高度获取
     */
    private static int[] ATTRS = {
            R.attr.windowActionBarOverlay,
            R.attr.actionBarSize
    };
    /**
     * 创建view时用到
     */
    private Context mContext;
    /**
     * base setView
     */
    private ContentFrameLayout mContentView;
    /**
     * 用户定义的视图
     */
    private View mUserView;
    private Toolbar mToolbar;
    /**
     * 视图构造器
     */
    private LayoutInflater mInflater;
    private TextView mTvTitle;
    private TextView mTvRight;

    public ToolBarHelper(Context context, Object view) {
        this.mContext = context;
        mInflater = LayoutInflater.from(mContext);
        if (view instanceof Integer) {
            mUserView = mInflater.inflate((Integer) view, null);
        } else if (view instanceof View) {
            mUserView = (View) view;
        } else {
            throw new ClassCastException("ToolBarHelper setLayout() type must be int or View");
        }
        //初始化整个内容
        initContentView();
        //初始化用户定义的布局
        intiUserView();
        //初始化toolBar
        initToolBar();
    }

    @SuppressLint("RestrictedApi")
    private void initContentView() {
        //直接创建一个帧布局，作为视图容器的父容器
        mContentView = new ContentFrameLayout(mContext);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mContentView.setLayoutParams(params);
    }

    private void intiUserView() {
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        //设置自定义属性到控件中
        TypedArray typedArray = mContext.getTheme().obtainStyledAttributes(ATTRS);
        //主题中定义的悬浮标志
        boolean overly = typedArray.getBoolean(0, false);
        //获取主题中定义的toolBar的高度
        @SuppressLint({"ResourceType", "PrivateResource"})
        int toolBarSize = (int) typedArray.getDimension(1,
                mContext.getResources().getDimension(R.dimen.abc_action_bar_default_height_material));
        typedArray.recycle();
        //如果是悬浮状态，则不需要设置间距
        params.topMargin = overly ? 0 : toolBarSize;
        mContentView.addView(mUserView, params);
    }

    private void initToolBar() {
        /*
         * 通过inflater获取toolBar的布局文件
         */
        View toolBar = mInflater.inflate(R.layout.toolbar_layout, mContentView);
        mToolbar = toolBar.findViewById(R.id.toolbar);
        mTvTitle = toolBar.findViewById(R.id.center_title);
        mTvRight = toolBar.findViewById(R.id.right_title);
    }


    public ContentFrameLayout getContentView() {
        return mContentView;
    }

    public Toolbar getToolBar() {
        return mToolbar;
    }

    /**
     * 获取中间视图
     *
     * @return 中间视图
     */
    public TextView getTitleView() {
        return mTvTitle;
    }

    /**
     * 设置中间标题
     *
     * @param txt 文本
     */
    public void setTitleText(String txt) {
        mTvTitle.setText(txt);
    }

    /**
     * 获取右侧视图
     *
     * @return 右侧视图
     */
    public TextView getRightView() {
        return mTvRight;
    }

    /**
     * 设置右侧标题
     *
     * @param txt 文本
     */
    public void setRightText(String txt) {
        mTvRight.setText(txt);
    }
}