package com.pm.amass.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.callback.NavCallback;
import com.alibaba.android.arouter.facade.callback.NavigationCallback;
import com.alibaba.android.arouter.launcher.ARouter;
import com.pm.amass.MainActivity;
import com.pm.amass.R;
import com.pm.amass.RoutePath;
import com.pm.amass.login.LoginActivity;
import com.pm.amass.manager.AppManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

/**
 * @author pmcho
 */
public class SplashActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "SplashActivity";
    private static final int SPLASH_TIMER_WHAT = 1;

    /**
     * 跳过广告
     */
    private Button mBtnSkip;
    /**
     * 立即体验
     */
    private Button mBtnStart;

    Handler mSplashHandler;
    SplashViewModel mViewModel;

    /**
     * 已经登录
     */
    private boolean isAlreadyLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        mViewModel = ViewModelProviders.of(this).get(SplashViewModel.class);
        subscribeViewModel();
        initView();
    }

    private void subscribeViewModel() {
       /* mViewModel.getLoginState()
                .observe(this, aBoolean -> {
                            Log.d(TAG, "subscribeViewModel: aBoolean="+aBoolean);
                            isAlreadyLogin = aBoolean;
                        }
                );*/
        String accountOrPhone = AppManager.getInstance().getAccountOrPhone();
        String uid = AppManager.getInstance().getUid();
        if (!TextUtils.isEmpty(accountOrPhone) && !TextUtils.isEmpty(uid)) {
            isAlreadyLogin = true;
        } else {
            isAlreadyLogin = false;
        }
       /* mViewModel.getToken().observe(this, aBoolean -> {
            if (!aBoolean) {
                ToastHelper.makeToast(SplashActivity.this, "获取token失败").show();
            }
        });*/
    }

    @Override
    protected void onStart() {
        super.onStart();
        mSplashHandler = new Handler(msg -> {
            if (msg.what == SPLASH_TIMER_WHAT) {
                startMainActivity();
            }
            return true;
        });
        mSplashHandler.sendEmptyMessageDelayed(SPLASH_TIMER_WHAT, 5 * 1000);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mSplashHandler.removeMessages(SPLASH_TIMER_WHAT);
        mSplashHandler = null;
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    private void initView() {
        mBtnSkip = (Button) findViewById(R.id.btn_skip);
        mBtnSkip.setOnClickListener(this);
        mBtnStart = (Button) findViewById(R.id.btn_start);
        mBtnStart.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.btn_skip:
                startMainActivity();
                break;
            case R.id.btn_start:
                startMainActivity();
                break;
        }
    }

    private void startMainActivity() {
        Intent intent;
        if (isAlreadyLogin) {
            intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        } else {
//            intent = new Intent(this, LoginActivity.class);
            ARouter.getInstance()
                    .build(RoutePath.LOGIN_ACTIVITY)
                    .navigation(SplashActivity.this, 200, new NavCallback() {
                        @Override
                        public void onArrival(Postcard postcard) {
                        }
                    });
        }
        finish();
    }

    private static class SplashTimer extends CountDownTimer {

        /**
         * @param millisInFuture    The number of millis in the future from the call
         *                          to {@link #start()} until the countdown is done and {@link #onFinish()}
         *                          is called.
         * @param countDownInterval The interval along the way to receive
         *                          {@link #onTick(long)} callbacks.
         */
        public SplashTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
        }

        @Override
        public void onFinish() {
        }
    }
}
