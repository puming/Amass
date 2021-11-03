package com.pm.login;

import android.content.Context;
import android.util.Log;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Interceptor;
import com.alibaba.android.arouter.facade.callback.InterceptorCallback;
import com.alibaba.android.arouter.facade.template.IInterceptor;
import com.basics.base.BaseApplication;
import com.tencent.mmkv.MMKV;

/**
 * pm
 */
//@Interceptor(priority = 1)
public class LoginInterceptor implements IInterceptor {
    private static final String TAG = "LoginInterceptor";

    @Override
    public void process(Postcard postcard, InterceptorCallback callback) {
        String path = postcard.getPath();
        Log.d(TAG, "process: path=" + path);
        MMKV mmkv = BaseApplication.getAppComponent().getLoginMMKV();
        boolean isLogin = false;
        if(isLogin){
            //不拦截
            callback.onContinue(postcard);
        }else {
            switch (path){
                case "/login/login/LoginActivity":
                    //不需要登录的界面，放行
                    callback.onContinue(postcard);
                    break;
                default:
                    //需要登录的拦截，拦截
                    callback.onInterrupt(null);
                    break;
            }
        }
    }

    @Override
    public void init(Context context) {
        Log.d(TAG, "init: ");
    }
}
