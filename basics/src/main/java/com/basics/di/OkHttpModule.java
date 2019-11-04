package com.basics.di;

import android.text.TextUtils;

import com.common.retrofit.LiveDataCallAdapterFactory;
import com.basics.http.interceptor.AddCookiesInterceptor;
import com.basics.http.interceptor.LoggingInterceptor;
import com.basics.http.interceptor.ReceivedCookiesInterceptor;
import com.basics.http.interceptor.TokenInterceptor;
import com.common.BuildConfig;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.concurrent.TimeUnit;

import javax.inject.Named;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;

import dagger.Module;
import dagger.Provides;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.CallAdapter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author pm
 * @date 2018/12/3
 * @email puming@zdsoft.cn
 */
@Module
public class OkHttpModule {
    private HttpUrl mHttpUrl;

    public OkHttpModule(Builder builder) {
        this.mHttpUrl = builder.httpUrl;
    }

    X509TrustManager[] trustManager = new X509TrustManager[]{
            new X509TrustManager() {
                @Override
                public void checkClientTrusted(X509Certificate[] chain, String authType) throws
                        CertificateException {
                }

                @Override
                public void checkServerTrusted(X509Certificate[] chain, String authType) throws
                        CertificateException {
                }

                @Override
                public X509Certificate[] getAcceptedIssuers() {
                    return new java.security.cert.X509Certificate[]{};
                }
            }
    };

    @Provides
    OkHttpClient provideOkHttpClient() {
        SSLContext sslContext = null;
        try {
            sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustManager, new SecureRandom());
            SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }
        /*//缓存路径
        File httpCacheDirectory = new File(context.getApplicationContext().getCacheDir(), "responses");
        //10M
        int cacheSize = 10 * 1024 * 1024;
        Cache cache = new Cache(httpCacheDirectory, cacheSize);*/
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .connectTimeout(30 * 1000, TimeUnit.MILLISECONDS)
                .readTimeout(30 * 1000, TimeUnit.MILLISECONDS)
                .writeTimeout(30 * 1000, TimeUnit.MILLISECONDS)
                .retryOnConnectionFailure(true)
                .addInterceptor(new ReceivedCookiesInterceptor())
                .addInterceptor(new LoggingInterceptor())
                .addInterceptor(new TokenInterceptor())
                .addInterceptor(new AddCookiesInterceptor())
                /*.addNetworkInterceptor(cacheInterceptor)
                .cache(cache)*/
                .sslSocketFactory(sslContext.getSocketFactory(), trustManager[0]);
        if (BuildConfig.DEBUG) {
            builder.addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY));
        }
        return builder.build();
    }

    @Named("live")
    @Provides
    Retrofit provideRetrofit(HttpUrl domain, OkHttpClient client) {
        return new Retrofit.Builder().client(client)
                .baseUrl(domain)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(new LiveDataCallAdapterFactory())
                .build();
    }

    @Named("rx")
    @Provides
    Retrofit provideRxRetrofit(HttpUrl domain, OkHttpClient client, CallAdapter.Factory factory) {
        return new Retrofit.Builder().client(client)
                .baseUrl(domain)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(factory)
                .build();
    }

    @Provides
    CallAdapter.Factory provideFactory() {
        return RxJava2CallAdapterFactory.create();
    }


    @Provides
    HttpUrl provideHttpUrl() {
        return mHttpUrl;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private HttpUrl httpUrl;

        public Builder baseUrl(String baseUrl) {
            if (TextUtils.isEmpty(baseUrl)) {
                throw new NullPointerException("BaseUrl can not be empty");
            }
            this.httpUrl = HttpUrl.parse(baseUrl);
            return this;
        }

        public OkHttpModule build() {
            return new OkHttpModule(this);
        }
    }

}
