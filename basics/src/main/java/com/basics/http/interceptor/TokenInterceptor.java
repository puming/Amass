package com.basics.http.interceptor;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.basics.base.BaseApplication;
import com.basics.di.AppComponent;
import com.basics.http.token.ITokenService;
import com.basics.http.token.Token;
import com.common.retrofit.IRetrofitManager;
import com.common.utils.NetworkUtils;

import java.io.IOException;
import java.util.Set;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Call;

/**
 * @author pm
 * @date 2019/1/11
 * @email puming@zdsoft.cn
 */
public class TokenInterceptor implements Interceptor {
    private static final boolean DEBUG = false;
    private static final String TAG = "TokenInterceptor";

    @Override
    public Response intercept(Chain chain) throws IOException {
        if (!NetworkUtils.isNetWorkConnected(BaseApplication.getAppComponent().getApplication())) {
            Log.d(TAG, "intercept: 无网络连接");
        }
        Request originalRequest = chain.request();
        HttpUrl originalHttpUrl = originalRequest.url();
        String originalUrl = originalHttpUrl.toString();
        Response originalResponse = chain.proceed(originalRequest);
        boolean hasCheck = originalUrl.contains("/api/index/tokencheck");
        if (hasCheck) {
            return originalResponse;
        }
        //根据和服务端的约定判断token过期
        if (isTokenExpired(originalResponse)) {
            if (DEBUG) {
                Log.d(TAG, "originalResponse.code=" + originalResponse.code());
                Log.d(TAG, "intercept: originalUrl=" + originalUrl);
            }
            //同步请求方式，获取最新的Token
            String newToken = requestNewToken();
            String method = originalRequest.method();
            if ("POST".equals(method)) {
                //初始化新的FormBody
                FormBody.Builder newFormBody = new FormBody.Builder();
                FormBody oldFormBody = (FormBody) originalRequest.body();
                for (int i = 0; i < oldFormBody.size(); i++) {
                    //去除旧的参数中的token字段值
                    String encodedName = oldFormBody.encodedName(i);
                    if (DEBUG) {
                        Log.d(TAG, "intercept: encodedName=" + encodedName);
                    }
                    if (encodedName.equals("token")) {
                        continue;
                    }
                    //在新的FormBody增加旧的FormBody的参数
                    newFormBody.addEncoded(encodedName, oldFormBody.encodedValue(i));
                }
                //添加新的token到FormBody
                newFormBody.add("token", newToken);

                //重新生成URL
                HttpUrl url = chain.request().url()
                        .newBuilder()
                        .build();

                //重新构建Request
                Request newRequest = chain.request().newBuilder()
                        .post(newFormBody.build())
                        .url(url)
                        .build();
                //关闭原先的请求响应
                ResponseBody body = originalResponse.body();
                if (body != null) {
                    body.close();
                }
                return chain.proceed(newRequest);
            } else if ("GET".equals(method)) {
                Set<String> queryParameterNames = originalHttpUrl.queryParameterNames();
                HttpUrl.Builder newBuilder = originalHttpUrl.newBuilder();
                for (String key : queryParameterNames) {
                    if (DEBUG) {
                        Log.d(TAG, "intercept: key=" + key);
                    }
                    if (("token").equals(key)) {
                        continue;
                    }
                    newBuilder.addQueryParameter(key, originalHttpUrl.queryParameter(key));
                }
                //使用新的Token，创建新的请求
                newBuilder.addQueryParameter("token", newToken);
                Request newRequest = chain.request()
                        .newBuilder()
                        .url(newBuilder.build())
//                    .header("Authorization", "Basic " + newToken)
                        .build();
                return chain.proceed(newRequest);
            }
        }
        return originalResponse;
    }

    /**
     * 根据Response，判断Token是否失效
     *
     * @param response
     * @return
     */
    private boolean isTokenExpired(Response response) {
        if (response.code() == 707) {
            return true;
        }
        return false;
    }

    /**
     * 同步请求方式，获取最新的Token
     *
     * @return
     */
    private String requestNewToken() {
        // 通过获取token的接口，同步请求接口
        AppComponent appComponent = BaseApplication.getAppComponent();
        ITokenService iTokenService = appComponent
                .getRetrofitManager()
                .obtainRetrofitService(ITokenService.class, IRetrofitManager.AdapterType.DEFAULT);
        Call<Token> call = iTokenService.requestToken(ITokenService.KEY);
        Token token = null;
        try {
            token = call.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 同步
        Log.d(TAG, "requestNewToken: token" + token);
        if (token == null) {
            return "";
        } else {
            String tokenStr = token.getData().getToken().getInfo();
            appComponent.getLoginSharedPreferences()
                    .edit()
                    .putString("token", tokenStr)
                    .apply();
            return tokenStr;
        }
    }
}
