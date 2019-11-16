package com.pm.amass.login;

import android.app.Application;
import android.content.SharedPreferences;

import com.basics.base.BaseViewModel;
import com.basics.repository.LiveNetworkBoundResource;
import com.basics.repository.Resource;
import com.common.retrofit.ApiResponse;
import com.common.retrofit.IRetrofitManager;
import com.pm.amass.MainApplication;
import com.pm.amass.api.ILoginService;
import com.pm.amass.bean.Result;
import com.pm.amass.bean.Token;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;

import java.util.HashMap;

/**
 * @author pm
 */
public class SignInViewModel extends BaseViewModel {

    private final IRetrofitManager retrofitManager;
    private static final String KEY = "31541eefbdf8e5b8fecb87de02720b05";

    public SignInViewModel(@NonNull Application application) {
        super(application);
        retrofitManager = MainApplication.getAppComponent().getRetrofitManager();
    }

    public MediatorLiveData<Resource<Token>> getToken() {
        LiveNetworkBoundResource<Token> boundResource = new LiveNetworkBoundResource<Token>() {
            @NonNull
            @Override
            protected LiveData<ApiResponse<Token>> createCall() {
                ILoginService loginService = retrofitManager.obtainRetrofitService(ILoginService.class);
                return loginService.requestToken(KEY);
            }

            @Override
            protected void onFetchFailed() {

            }
        };
        return boundResource.getAsLiveData();
    }

    public MediatorLiveData<Resource<Result>> getSmsCode(String phone, String token) {
        LiveNetworkBoundResource<Result> boundResource = new LiveNetworkBoundResource<Result>() {
            @NonNull
            @Override
            protected LiveData<ApiResponse<Result>> createCall() {
                ILoginService loginService = retrofitManager.obtainRetrofitService(ILoginService.class);
                HashMap<String, String> fieldMap = new HashMap<>(4);
                fieldMap.put("phone", phone);
                fieldMap.put("token", token);
                fieldMap.put("action", "login");
                fieldMap.put("source", "student");

                return loginService.requestSmsCode(fieldMap);
            }

            @Override
            protected void onFetchFailed() {

            }
        };
        return boundResource.getAsLiveData();
    }

    public MediatorLiveData<Resource<Result>> getSignInData(String phone,String code) {
        LiveNetworkBoundResource<Result> boundResource = new LiveNetworkBoundResource<Result>() {
            @NonNull
            @Override
            protected LiveData<ApiResponse<Result>> createCall() {
                ILoginService loginService = retrofitManager.obtainRetrofitService(ILoginService.class);
                HashMap<String, String> fieldMap = new HashMap<>(12);
                fieldMap.put("token",readTokenFromSp());
                fieldMap.put("phone",phone);
                fieldMap.put("yzm",code);
                fieldMap.put("way","phone");
                fieldMap.put("source","student");
                return loginService.requestSignIn(fieldMap);
            }

            @Override
            protected void onFetchFailed() {

            }
        };
        return boundResource.getAsLiveData();
    }

    void writePhoneToSp(String phone) {
        SharedPreferences sp = MainApplication.getAppComponent().getLoginSharedPreferences();
        sp.edit().putString("phone", phone).apply();
    }

    String readPhoneFromSp() {
        SharedPreferences sp = MainApplication.getAppComponent().getLoginSharedPreferences();
        return sp.getString("phone", "");
    }

    void writeTokenToSp(String token) {
        SharedPreferences sp = MainApplication.getAppComponent().getLoginSharedPreferences();
        sp.edit().putString("token", token).apply();
    }

    String readTokenFromSp() {
        SharedPreferences sp = MainApplication.getAppComponent().getLoginSharedPreferences();
        return sp.getString("token", "");
    }


}
