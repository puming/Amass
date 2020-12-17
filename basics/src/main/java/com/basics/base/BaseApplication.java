package com.basics.base;

import android.app.Application;
import android.content.Context;

import com.alibaba.android.arouter.launcher.ARouter;
import com.basics.BuildConfig;
import com.basics.di.AppComponent;
import com.basics.di.DaggerAppComponent;
import com.basics.di.OkHttpModule;
import com.basics.utils.CrashHandler;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.CsvFormatStrategy;
import com.orhanobut.logger.DiskLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;
import com.tencent.mmkv.MMKV;

/**
 * @author pm
 */
public class BaseApplication extends Application {

    protected static AppComponent mAppComponent;
    protected static Context sContext;


    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        sContext = base;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        if (BuildConfig.DEBUG) {
            ARouter.openLog();
            ARouter.openDebug();
        }
        ARouter.init(this);
        mAppComponent = DaggerAppComponent.builder()
                .application(this)
                .okHttpModule(OkHttpModule.builder().baseUrl(baseUrlString()).build())
                .build();
        FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder()
/*                .showThreadInfo(true)
                .methodCount(2)
                .methodOffset(5)*/
                .tag("amass")
                .build();
        Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy) {
            @Override
            public boolean isLoggable(int priority, String tag) {
                return BuildConfig.DEBUG;
            }
        });

        FormatStrategy diskStrategy = CsvFormatStrategy.newBuilder()
                .tag("custom")
                .build();

        Logger.addLogAdapter(new DiskLogAdapter(diskStrategy));
        String rootDir = MMKV.initialize(this);
        CrashHandler.getInstance(this);
    }

    public static AppComponent getAppComponent() {
        return mAppComponent;
    }

    /**
     * 子模块如果有网络通信一定要重写此方法，返回具体的域名。
     *
     * @return domain
     */
    protected String baseUrlString() {
        return BuildConfig.DEFAULT_DOMAIN;
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
    }

}
