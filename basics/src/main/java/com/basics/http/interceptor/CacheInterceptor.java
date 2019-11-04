package com.basics.http.interceptor;

import android.util.Log;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author pm
 * @date 2018/11/30
 * @email puming@zdsoft.cn
 */
public class CacheInterceptor implements Interceptor {
    private static final String TAG = "CacheInterceptor";

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Log.d(TAG, "intercept: " + request.url().toString());
        /*//没有网络的时候强制使用缓存
        if (!NetworkUtil.hasInternet(context.getApplicationContext())) {
            request = request.newBuilder()
                    .cacheControl(CacheControl.FORCE_CACHE)
                    .build();
        }

        Response originalResponse = chain.proceed(request);
        if (NetworkUtil.hasInternet(context.getApplicationContext())) {
            //每个请求自己控制自己的缓存时效,参看IHttpService类中的频道内容接口
            String cacheControl = request.cacheControl().toString();
            Log.d(TAG, "intercept: cacheControl=" + cacheControl);
            Log.d(TAG, "intercept: ");
            return originalResponse.newBuilder()
                    .removeHeader("Pragma")
                    .header("Cache-Control", "public ,max-age=" + cacheControl)
                    .build();
        } else {
            // tolerate 4-weeks stale　没有网络会走到这边
            int maxStale = 60 * 60 * 24 * 28;
            return originalResponse.newBuilder()
                    .removeHeader("Pragma")
                    .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                    .build();
        }*/
        return null;
    }
}
