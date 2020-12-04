package com.basics.di;

import android.app.Application;
import android.content.SharedPreferences;

import com.common.imageloader.BaseImageLoaderStrategy;
import com.common.imageloader.ImageLoader;
import com.common.retrofit.IRetrofitManager;
import com.common.utils.AppExecutors;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;

@Singleton
@Component(modules = {AppModule.class, ConfigModule.class, OkHttpModule.class})
public interface AppComponent {
    IRetrofitManager getRetrofitManager();

    OkHttpClient getOkHttpClient();

    HttpUrl getHttpUrl();

    BaseImageLoaderStrategy getBaseImageLoaderStrategy();

    ImageLoader getImageLoader();

    Application getApplication();

    AppExecutors getAppExecutors();

    SharedPreferences getSharedPreferences();

    @Named("login")
    SharedPreferences getLoginSharedPreferences();

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);

        Builder okHttpModule(OkHttpModule okHttpModule);

        AppComponent build();
    }
}
