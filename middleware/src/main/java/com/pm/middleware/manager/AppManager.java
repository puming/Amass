package com.pm.middleware.manager;

import com.basics.base.BaseApplication;

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
        return BaseApplication.getAppComponent().getLoginSharedPreferences().getString("uid", "");
    }

    public String getAccountOrPhone() {
        return BaseApplication.getAppComponent().getLoginSharedPreferences().getString("account", "");
    }

    public String getToken() {
        return BaseApplication.getAppComponent().getLoginSharedPreferences().getString("token", "");
    }

    public void clear(){
        boolean commit = BaseApplication.getAppComponent().getLoginSharedPreferences().edit().clear().commit();
    }


}
