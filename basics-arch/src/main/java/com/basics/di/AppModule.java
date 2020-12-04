package com.basics.di;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.common.imageloader.BaseImageLoaderStrategy;
import com.common.imageloader.ImageLoader;
import com.common.imageloader.glide.GlideImageLoaderStrategy;
import com.common.retrofit.IRetrofitManager;
import com.common.retrofit.RetrofitManager;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

/**
 * @author pm
 */
@Module(includes = AppModule.Declarations.class)
public final class AppModule {

    @Singleton
    @Provides
    ImageLoader provideImageLoader(BaseImageLoaderStrategy imageLoaderStrategy) {
        return new ImageLoader(imageLoaderStrategy);
    }

    @Singleton
    @Provides
    BaseImageLoaderStrategy provideBaseImageLoaderStrategy() {
//        默认使用Glide图片加载框架
        return new GlideImageLoaderStrategy();
    }

    @Singleton
    @Provides
    SharedPreferences provideSharedPreferences(Application application) {
        Context context = application.getApplicationContext();
        return context.getSharedPreferences(context.getPackageName(), Context.MODE_PRIVATE);
    }

    @Named("login")
    @Singleton
    @Provides
    SharedPreferences provideLoginSharedPreferences(Application application) {
        Context context = application.getApplicationContext();
        return context.getSharedPreferences("login", Context.MODE_PRIVATE);
    }

//    @Binds
//    abstract IRetrofitManager bindRetrofitManager(RetrofitManager retrofitManager);

    @Module
    public interface Declarations {
        @Binds
        IRetrofitManager bindRetrofitManager(RetrofitManager retrofitManager);
    }
}
