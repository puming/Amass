package com.basics.http.interceptor;

import com.basics.base.BaseApplication;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author pm
 * @date 2018/11/29
 * @email puming@zdsoft.cn
 */
public class AddCookiesInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request.Builder builder = chain.request().newBuilder();
        String cookieStr = BaseApplication.getAppComponent()
                .getSharedPreferences()
                .getString("cookieStr", "");
//        List<String> cookies = JSONObject.parseArray(cookieStr, String.class);
        List<String> cookies = new Gson().fromJson(cookieStr, List.class);
        if (cookies != null) {
            for (String cookie : cookies) {
                builder.addHeader("Cookie", cookie);
                // This is done so I know which headers are being added; this interceptor is used after the normal logging of OkHttp
            }
        }
        return chain.proceed(builder.build());
    }
}
