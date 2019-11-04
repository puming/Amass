package com.basics.http.interceptor;

import com.basics.base.BaseApplication;
import com.google.gson.Gson;
import com.orhanobut.logger.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * @author pm
 * @date 2018/11/29
 * @email puming@zdsoft.cn
 */
public class ReceivedCookiesInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {

        Response originalResponse = chain.proceed(chain.request());
        if (!originalResponse.headers("Set-Cookie").isEmpty()) {
            List<String> cookies = new ArrayList<>();
            for (String header : originalResponse.headers("Set-Cookie")) {
                cookies.add(header);
            }
            String cookieStr = new Gson().toJson(cookies);
            Logger.json(cookieStr);
//            String cookieStr = JSONObject.toJSONString(cookies);
//            SPUtil.putData(Constant.SP.SP, Constant.SP.SESSION_ID, cookieStr);
            BaseApplication.getAppComponent()
                    .getSharedPreferences()
                    .edit()
                    .putString("cookieStr", cookieStr)
                    .apply();
        }

        return originalResponse;
    }
}
