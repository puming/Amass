package com.pm.amass;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.util.Log;

import com.basics.base.BaseApplication;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 * @author pm
 * @date 2019/11/15
 * @email puming@zdsoft.cn
 */
public class MainApplication extends BaseApplication {
    private int activityCount = 0;
    private static MainApplication sInstance;
    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    protected String baseUrlString() {
        return BuildConfig.DOMAIN;
    }

    public static MainApplication getApplication() {
        return sInstance;
    }


    private void initGlobeActivity() {
        registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                Log.d("onActivityCreated===", activity + "");
            }

            @Override
            public void onActivityDestroyed(Activity activity) {
                Log.d("onActivityDestroyed===", activity + "");
            }

            /** Unused implementation **/
            @Override
            public void onActivityStarted(Activity activity) {
                Log.d("onActivityStarted===", activity + "");
                activityCount++;
            }

            @Override
            public void onActivityResumed(Activity activity) {
                Log.d("onActivityResumed===", activity + "");
            }

            @Override
            public void onActivityPaused(Activity activity) {
                Log.d("onActivityPaused===", activity + "");
            }

            @Override
            public void onActivityStopped(Activity activity) {
                Log.d("onActivityStopped===", activity + "");
                activityCount--;
            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
            }
        });
    }

    public int getAppCount() {
        return activityCount;
    }

    public boolean isApplicationForground() {
        return getAppCount() > 0;
    }

    public static String getProcessName() {
        try {
            File file = new File("/proc/" + android.os.Process.myPid() + "/" + "cmdline");
            BufferedReader mBufferedReader = new BufferedReader(new FileReader(file));
            String processName = mBufferedReader.readLine().trim();
            mBufferedReader.close();
            return processName;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
