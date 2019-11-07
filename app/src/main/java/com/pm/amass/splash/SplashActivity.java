package com.pm.amass.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;

import com.pm.amass.MainActivity;
import com.pm.amass.R;

import java.util.Timer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @author pmcho
 */
public class SplashActivity extends AppCompatActivity implements View.OnClickListener {
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        initView();
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
        mSplashHandler.sendEmptyMessageDelayed(SPLASH_TIMER_WHAT, 3 * 1000);
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
        startActivity(new Intent(this, MainActivity.class));
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
