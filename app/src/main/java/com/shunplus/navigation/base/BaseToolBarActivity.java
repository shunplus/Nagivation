package com.shunplus.navigation.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.shunplus.navigation.R;

/**
 * @author xushun on  2020/5/10 20:45.
 * Email：shunplus@163.com
 * Des：
 */
public abstract class BaseToolBarActivity extends AppCompatActivity {
    private LinearLayout parentLinearLayout ;
    private TextView mTvTitle;
    private TextView mTvRight;
    private Toolbar mToolbar;
    private boolean isShow=true;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initContentView(R.layout.base_activity_layout);
        setContentView(getLayoutId());
        initToolBar();
        setBackIcon();
        init();
    }

    protected abstract int getLayoutId();
    protected abstract void init();

    private void initContentView(@LayoutRes int layoutResID) {
        ViewGroup viewGroup = (ViewGroup) findViewById(android.R.id.content);
        viewGroup.removeAllViews();
        parentLinearLayout  = new LinearLayout(this);
        parentLinearLayout .setOrientation(LinearLayout.VERTICAL);
        //  add parentLinearLayout in viewGroup
        viewGroup.addView(parentLinearLayout);
        //  add the layout of BaseActivity in parentLinearLayout
        LayoutInflater.from(this).inflate(layoutResID, parentLinearLayout, true);
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        //  added the sub-activity layout id in parentLinearLayout
        LayoutInflater.from(this).inflate(layoutResID, parentLinearLayout, true);
    }

    private void setBackIcon(){
        if (null != mToolbar && isShow) {
            mToolbar.setNavigationIcon(R.mipmap.icon_back);
            mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    BaseToolBarActivity.this.onBackPressed();
                }
            });
        }
    }

    public Toolbar getToolbar() {
        return (Toolbar) findViewById(R.id.toolbar);
    }

    private void  initToolBar(){
        mToolbar=getToolbar();
        mTvTitle=findViewById(R.id.center_title);
    };

    public void setShowBack(boolean isShow){
        if (isShow){
            mToolbar.setNavigationIcon(R.mipmap.icon_back);
        }else {
            mToolbar.setNavigationIcon(null);
        }
    }



    /**
     * set Title
     * @param title
     */
    public void setToolBarTitle(CharSequence title) {
        if (mTvTitle != null) {
            mTvTitle.setText(title);
        } else {
            mToolbar.setTitle(title);
            setSupportActionBar(mToolbar);
        }
    }


}
