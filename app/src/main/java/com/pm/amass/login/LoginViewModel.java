package com.pm.amass.login;

import android.app.Application;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;

import com.basics.base.BaseViewModel;
import com.basics.repository.LiveNetworkBoundResource;
import com.basics.repository.Resource;
import com.basics.repository.Result;
import com.common.retrofit.ApiResponse;
import com.pm.amass.MainApplication;
import com.pm.amass.api.ILoginService;
import com.pm.amass.bean.ResultInfo;
import com.pm.amass.bean.Token;

import java.util.HashMap;
import java.util.Map;

/**
 * @author pmcho
 */
public class LoginViewModel extends BaseViewModel {

    private ILoginService mLoginService;

    public LoginViewModel(@NonNull Application application) {
        super(application);
        mLoginService = mRetrofitManager.obtainRetrofitService(ILoginService.class);
    }

    public MediatorLiveData<Resource<Result<Token>>> getToken() {
        LiveNetworkBoundResource<Result<Token>> boundResource = new LiveNetworkBoundResource<Result<Token>>() {
            @NonNull
            @Override
            protected LiveData<ApiResponse<Result<Token>>> createCall() {
                return mLoginService.requestToken(ILoginService.KEY);
            }

            @Override
            protected void onFetchFailed() {

            }
        };
        return boundResource.getAsLiveData();
    }

    public MediatorLiveData<Resource<ResultInfo>> getSmsCode(String phone, String action) {
        LiveNetworkBoundResource<ResultInfo> boundResource = new LiveNetworkBoundResource<ResultInfo>() {
            @NonNull
            @Override
            protected LiveData<ApiResponse<ResultInfo>> createCall() {
                HashMap<String, String> fieldMap = new HashMap<>(4);
                fieldMap.put("phone", phone);
                fieldMap.put("token", readTokenFromSp());
                fieldMap.put("action", action);
                fieldMap.put("source", "student");

                return mLoginService.requestSmsCode(fieldMap);
            }

            @Override
            protected void onFetchFailed() {

            }
        };
        return boundResource.getAsLiveData();
    }

    public MediatorLiveData<Resource<ResultInfo>> getSignInData(Map fieldMap) {
        LiveNetworkBoundResource<ResultInfo> boundResource = new LiveNetworkBoundResource<ResultInfo>() {
            @NonNull
            @Override
            protected LiveData<ApiResponse<ResultInfo>> createCall() {
                fieldMap.put("token",readTokenFromSp());
                fieldMap.put("source","student");
                return mLoginService.requestSignIn(fieldMap);
            }

            @Override
            protected void onFetchFailed() {

            }
        };
        return boundResource.getAsLiveData();
    }

    public MediatorLiveData<Resource<ResultInfo>> getSignUpData(Map fieldMap) {
        LiveNetworkBoundResource<ResultInfo> boundResource = new LiveNetworkBoundResource<ResultInfo>() {
            @NonNull
            @Override
            protected LiveData<ApiResponse<ResultInfo>> createCall() {
                return mLoginService.requestSignUp(fieldMap);
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
