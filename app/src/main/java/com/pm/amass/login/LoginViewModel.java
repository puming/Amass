package com.pm.amass.login;

import android.app.Application;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;

import com.basics.base.BaseViewModel;
import com.basics.repository.LiveNetworkBoundResource;
import com.basics.repository.Resource;
import com.basics.repository.Result;
import com.common.retrofit.ApiResponse;
import com.common.ux.ToastHelper;
import com.pm.amass.MainActivity;
import com.pm.amass.MainApplication;
import com.pm.amass.api.ILoginService;
import com.pm.amass.bean.ResultInfo;
import com.pm.amass.bean.Token;
import com.pm.amass.bean.UserResult;

import java.util.HashMap;
import java.util.Map;

/**
 * @author pmcho
 */
public class LoginViewModel extends BaseViewModel {

    private ILoginService mLoginService;
    private MediatorLiveData<Boolean> mLoginSuccessData = new MediatorLiveData<Boolean>();
    private MediatorLiveData<Resource<UserResult>> mUserResource;
    private SharedPreferences mLoginSp;

    public LoginViewModel(@NonNull Application application) {
        super(application);
        mLoginService = mRetrofitManager.obtainRetrofitService(ILoginService.class);
        mLoginSp = MainApplication.getAppComponent().getLoginSharedPreferences();
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

    public MediatorLiveData<Resource<UserResult>> getSignInData(Map fieldMap) {
        mUserResource = new LiveNetworkBoundResource<UserResult>() {
            @NonNull
            @Override
            protected LiveData<ApiResponse<UserResult>> createCall() {
                fieldMap.put("token", readTokenFromSp());
                fieldMap.put("source", "student");
                return mLoginService.requestSignIn(fieldMap);
            }

            @Override
            protected void onFetchFailed() {

            }
        }.getAsLiveData();
        return mUserResource;
    }

    public LiveData<Boolean> getLoginSuccessData(Map fieldMap) {
        mUserResource = new LiveNetworkBoundResource<UserResult>() {
            @NonNull
            @Override
            protected LiveData<ApiResponse<UserResult>> createCall() {
                fieldMap.put("token", readTokenFromSp());
                fieldMap.put("source", "student");
                return mLoginService.requestSignIn(fieldMap);
            }

            @Override
            protected void onFetchFailed() {

            }
        }.getAsLiveData();

        mLoginSuccessData.addSource(mUserResource, resultLogin -> {
            if (resultLogin.status == Resource.Status.SUCCEED) {
                UserResult user = resultLogin.data;
                if (user == null) {
                    mLoginSuccessData.setValue(false);
                } else {
                    writeUidToSp(String.valueOf(user.getId()));
                    writePhoneToSp(user.getPhone());
                    mLoginSuccessData.setValue(true);
                }
            } else if (resultLogin.status == Resource.Status.ERROR) {
                mLoginSuccessData.setValue(false);
            }
        });
        return mLoginSuccessData;
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
        SharedPreferences sp = MainApplication.getAppComponent().getSharedPreferences();
        sp.edit().putString("cache_phone", phone).apply();
    }

    String readPhoneFromSp() {
        SharedPreferences sp = MainApplication.getAppComponent().getSharedPreferences();
        return sp.getString("cache_phone", "");
    }

    void writeTokenToSp(String token) {
        mLoginSp.edit().putString("token", token).apply();
    }

    String readTokenFromSp() {
        return mLoginSp.getString("token", "");
    }

    void writeUidToSp(String token) {
        mLoginSp.edit().putString("uid", token).apply();
    }

    String readUidFromSp() {
        return mLoginSp.getString("uid", "");
    }

    void writeAccountToSp(String token) {
        mLoginSp.edit().putString("account", token).apply();
    }

    String readAccountFromSp() {
        return mLoginSp.getString("account", "");
    }
}
