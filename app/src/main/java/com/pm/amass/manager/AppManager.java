package com.pm.amass.manager;

import com.pm.amass.MainApplication;

/**
 * @author pm
 * @date 2019/11/26
 * @email puming@zdsoft.cn
 */
public class AppManager {
    private static volatile AppManager sInstance;

    public static AppManager getInstance() {
        if (sInstance == null) {
            synchronized (AppManager.class) {
                if (sInstance == null) {
                    sInstance = new AppManager();
                }
            }
        }
        return sInstance;
    }

    public String getUid() {
        return MainApplication.getAppComponent().getLoginSharedPreferences().getString("uid", "");
    }

    public String getAccountOrPhone() {
        return MainApplication.getAppComponent().getLoginSharedPreferences().getString("phone", "");
    }


}