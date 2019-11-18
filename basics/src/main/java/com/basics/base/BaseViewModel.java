package com.basics.base;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.basics.di.AppComponent;
import com.common.retrofit.IRetrofitManager;

/**
 * @author pmcho
 */
public class BaseViewModel extends AndroidViewModel {
    protected AppComponent mAppComponent;
    protected IRetrofitManager mRetrofitManager;

    public BaseViewModel(@NonNull Application application) {
        super(application);
        mAppComponent = BaseApplication.getAppComponent();
        mRetrofitManager = mAppComponent.getRetrofitManager();
    }
}
