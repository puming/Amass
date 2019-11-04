package com.basics.http.interceptor;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.basics.base.BaseApplication;
import com.common.utils.NetworkUtils;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author pm
 * @date 2019/1/11
 * @email puming@zdsoft.cn
 */
public class TokenInterceptor implements Interceptor {
    private static final boolean TEST = false;
    private static final String TAG = "TokenInterceptor";

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request originalRequest = chain.request();
        String url = originalRequest.url().toString();
        Context applicationContext = BaseApplication.getAppComponent().getApplication().getApplicationContext();
        if (!NetworkUtils.isNetWorkConnected(applicationContext)) {
            Log.e(TAG, "intercept: 无网络连接");
        }
        boolean hasSmsUrl = url.contains("common/sendSmsVerifyCode.json");
        boolean hasLoginUrl = url.contains("common/login/appAccessKeyLogin.json");
        boolean hasInitAppUrl = url.contains("remotedd/webrtc/room/getInitAppData.json");
        if (hasSmsUrl || hasLoginUrl || hasInitAppUrl) {
            return chain.proceed(originalRequest);
        }
        String token = BaseApplication.getAppComponent().getLoginSharedPreferences().getString("mockSessionId", "");
        //对 token 进行判空，如果为空，则不进行修改
        if (TextUtils.isEmpty(token)) {
            return chain.proceed(originalRequest);
        }
        if (TEST) {
            if (url.contains("remotedd/order/openOrderStep.json")) {
                token = "";
            }
        }

        Request tokenRequest = null;
        //往请求头中添加 token 字段
        tokenRequest = originalRequest.newBuilder()
                .header("mockSessionId", token)
                .build();
        return chain.proceed(tokenRequest);
    }
}
