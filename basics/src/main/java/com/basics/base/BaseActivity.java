package com.basics.base;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.common.utils.DensityUtil;
import com.common.utils.NetworkUtils;
import com.common.ux.LoadingDialog;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author pm
 * @date 2019/1/10
 * @email puming@zdsoft.cn
 */
public abstract class BaseActivity extends AppCompatActivity {

    protected Unbinder mUnbinder;
    protected boolean mNoNetworkDialog;
    protected LoadingDialog mLoadingDialog;

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        //动态修改density适配屏幕
        DensityUtil.setCustomDensity(this, 375);
        super.onCreate(savedInstanceState);
        mLoadingDialog = new LoadingDialog(this, LoadingDialog.STRIP_SHAPE);
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        setContentViewAfter();
    }

    @Override
    public void setContentView(View view) {
        super.setContentView(view);
        setContentViewAfter();
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        super.setContentView(view, params);
        setContentViewAfter();
    }

    /**
     * 在setContentView之后执行
     */
    protected void setContentViewAfter() {
        mUnbinder = ButterKnife.bind(this);
        setupView();
    }

    protected void setupView() {

    }

    protected void setNoNetworkDialog(boolean noNetworkDialog) {
        this.mNoNetworkDialog = noNetworkDialog;
    }

    protected void showDialog() {
        showDialog("加载中");
    }

    protected void showDialog(String text) {
        mLoadingDialog.setMessage(text);
        mLoadingDialog.show();
        //设置超时自动消失
        new Handler().postDelayed(() -> {
            //取消加载框
            if (hideDialog(0)) {
                //超时处理
            }
        }, 6000);
    }

    protected boolean hideDialog() {
        return hideDialog(500);
    }

    protected boolean hideDialog(long delayMillis) {
        if (mLoadingDialog != null) {
            if (mLoadingDialog.isShowing()) {
                new Handler().postDelayed(() -> {
                    //取消加载框
                    mLoadingDialog.dismiss();
                }, delayMillis);
                return true;
            }
        }
        return false;
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        boolean connected = NetworkUtils.isNetWorkConnected(this);
        if (!connected && !mNoNetworkDialog) {
            Toast.makeText(this, "无网络连接", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mLoadingDialog.isShowing()) {
            mLoadingDialog.dismiss();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
    }

    protected void showTokenTimeoutDialog() {

    }

    public void handleError(int errorCode) {
        handleError(errorCode, null);
    }

    public void handleError(int errorCode, String error) {

    }

    /**
     * 由子类实现
     */
    protected void startLoginActivity() {

    }
}
