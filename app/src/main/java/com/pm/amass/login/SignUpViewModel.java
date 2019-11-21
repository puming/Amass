package com.pm.amass.login;

import android.app.Application;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.ViewModel;

import com.basics.base.BaseViewModel;
import com.basics.repository.LiveNetworkBoundResource;
import com.basics.repository.Resource;
import com.common.retrofit.ApiResponse;
import com.pm.amass.MainApplication;
import com.pm.amass.api.ILoginService;
import com.pm.amass.bean.Result;

import java.util.HashMap;
import java.util.Map;

/**
 * @author pmcho
 */
public class SignUpViewModel extends BaseViewModel {

    private ILoginService mLoginService;

    public SignUpViewModel(@NonNull Application application) {
        super(application);
        mLoginService = mRetrofitManager.obtainRetrofitService(ILoginService.class);
    }

    public MediatorLiveData<Resource<Result>> getSmsCode(String phone) {
        LiveNetworkBoundResource<Result> boundResource = new LiveNetworkBoundResource<Result>() {
            @NonNull
            @Override
            protected LiveData<ApiResponse<Result>> createCall() {
                HashMap<String, String> fieldMap = new HashMap<>(4);
                fieldMap.put("phone", phone);
                fieldMap.put("token", readTokenFromSp());
                fieldMap.put("action", "login");
                fieldMap.put("source", "student");
                return mLoginService.requestSmsCode(fieldMap);
            }

            @Override
            protected void onFetchFailed() {

            }
        };
        return boundResource.getAsLiveData();
    }

    public MediatorLiveData<Resource<Result>> getSignUpData(Map fieldMap) {
        LiveNetworkBoundResource<Result> boundResource = new LiveNetworkBoundResource<Result>() {
            @NonNull
            @Override
            protected LiveData<ApiResponse<Result>> createCall() {

                return mLoginService.requestSignUp(fieldMap);
            }

            @Override
            protected void onFetchFailed() {

            }
        };
        return boundResource.getAsLiveData();
    }

    String readTokenFromSp() {
        SharedPreferences sp = MainApplication.getAppComponent().getLoginSharedPreferences();
        return sp.getString("token", "");
    }
}
