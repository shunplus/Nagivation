package com.shunplus.navigation.base;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.ContentFrameLayout;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.shunplus.navigation.utils.ToolBarHelper;
import com.trello.rxlifecycle2.components.support.RxFragment;

/**
 * @author xushun on  2020/5/13 11:04.
 * Email：shunplus@163.com
 * Des：
 */
public abstract class BaseFragmentM<V extends ViewDataBinding, VM extends BaseViewModel> extends RxFragment implements BaseView {
    public Toolbar mToolbar;
    protected V binding;
    protected VM viewModel;
    protected Context mContext;
    private int viewModelId;
    private ToolBarHelper mToolBarHelper;
    private BaseActivity mActivity;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getContext();
        mActivity = (BaseActivity) getActivity();
        initParam();
        //指出fragment愿意添加item到选项菜单
        setHasOptionsMenu(true);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //私有的初始化Databinding和ViewModel方法
        initViewDataBinding();
        //页面数据初始化方法
        initData();
        //页面事件监听的方法，一般用于ViewModel层转到View层的事件注册
        initViewObservable();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, setLayout(), container, false);
        mToolBarHelper = new ToolBarHelper(mActivity, binding.getRoot());
        mToolbar = mToolBarHelper.getToolBar();
        mActivity.setSupportActionBar(mToolbar);
        onCreateCustomToolBar(mToolbar);
        setToolbarListener();
        ContentFrameLayout contentView = mToolBarHelper.getContentView();
//        onBindView(savedInstanceState, contentView);
        return contentView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (binding != null) {
            binding.unbind();
        }
    }

    /**
     * 注入绑定
     */
    private void initViewDataBinding() {
        viewModelId = initVariableId();
        viewModel = initViewModel();
        binding.setVariable(viewModelId, viewModel);
        //支持LiveData绑定xml，数据改变，UI自动会更新
        binding.setLifecycleOwner(this);
        //让ViewModel拥有View的生命周期感应
//        getLifecycle().addObserver(viewModel);
        //注入RxLifecycle生命周期
//        viewModel.injectLifecycleProvider(this);
    }

    /**
     * 初始化根布局
     *
     * @return 布局layout的id
     */
    public abstract int setLayout();

    /**
     * 初始化ViewModel的id
     *
     * @return BR的id
     */
    public abstract int initVariableId();

    /**
     * 初始化ViewModel
     *
     * @return 继承BaseViewModel的ViewModel
     */
    public abstract VM initViewModel();


    /**
     * 创建ViewModel
     *
     * @param cls
     * @param <T>
     * @return
     */
    public <T extends ViewModel> T createViewModel(Fragment fragment, Class<T> cls) {
        return new ViewModelProvider(fragment, fragment.getDefaultViewModelProviderFactory()).get(cls);
    }


    /**
     * 切换页面
     *
     * @param id
     */
    protected void nav(int id) {
        NavHostFragment.findNavController(this).navigate(id);
    }

    /**
     * 返回上一页面
     */
    protected void nav_back() {
        NavHostFragment.findNavController(this).navigateUp();
    }

    /**
     * 带参数切换页面
     *
     * @param id
     * @param bundle
     */
    protected void nav_bundle(int id, Bundle bundle) {
        NavHostFragment.findNavController(this).navigate(id, bundle);
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
