package com.basics.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.common.widget.AppBar;

/**
 * @author pm
 * @date 2019/11/12
 * @email puming@zdsoft.cn
 * 默认带有AppBar
 */
public class AppBarActivity extends BaseActivity {

    private LinearLayout mAppRootView;
    private AppBar mAppBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initBaseView();
    }

    @Override
    public void setContentView(int layoutResID) {
        LayoutInflater.from(this).inflate(layoutResID, mAppRootView, true);
        setContentViewAfter();
    }

    @Override
    public void setContentView(View view) {
        mAppRootView.addView(view);
        setContentViewAfter();
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        mAppRootView.addView(view, params);
        setContentViewAfter();
    }

    protected void initBaseView() {
        ViewGroup mSystemRooView = findViewById(android.R.id.content);
        mSystemRooView.removeAllViews();
        mAppRootView = new LinearLayout(this);
        mAppRootView.setOrientation(LinearLayout.VERTICAL);
//        mAppRootView.setFitsSystemWindows(!mNoImmersion);
        mAppBar = new AppBar(this);
        initAppBar(mAppBar);
        mAppRootView.addView(mAppBar, 0, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        registerAppBarListener();
        mSystemRooView.addView(mAppRootView);
    }

    protected void registerAppBarListener() {
        mAppBar.getAppbarRightContainer().setOnClickListener(this::onClickAppBarRightView);
        mAppBar.getAppbarLeftContainer().setOnClickListener(this::onClickAppBarLeftView);
    }

    protected void onClickAppBarRightView(View view) {
    }

    protected void onClickAppBarLeftView(View view) {
        onBackPressed();
    }

    protected AppBar getAppBar() {
        return mAppBar;
    }

    /**
     * 子类可以根据需求定制AppBar
     *
     * @param appBar
     */
    protected void initAppBar(AppBar appBar) {
        appBar.showAppbarRightContainer(false)
                .showAppbarBackText(false);
    }

}
