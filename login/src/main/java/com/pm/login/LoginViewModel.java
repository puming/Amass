package com.pm.login;

import android.app.Application;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;

import com.basics.base.BaseApplication;
import com.basics.base.BaseViewModel;
import com.basics.repository.LiveNetworkBoundResource;
import com.basics.repository.Resource;
import com.basics.repository.Result;
import com.common.retrofit.ApiResponse;
import com.pm.login.api.ILoginService;
import com.pm.login.bean.ResultInfo;
import com.pm.login.bean.Token;
import com.pm.login.bean.UserResult;

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
        mLoginSp = BaseApplication.getAppComponent().getLoginSharedPreferences();
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
                UserResult.User user = resultLogin.data.getData();
                if (user == null) {
                    mLoginSuccessData.setValue(false);
                } else {
                    writeUidToSp(String.valueOf(user.getId()));
                    writeAccountToSp(user.getPhone());
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

    void writeCachePhoneToSp(String phone) {
        SharedPreferences sp = BaseApplication.getAppComponent().getSharedPreferences();
        sp.edit().putString("cache_phone", phone).apply();
    }

    String readCachePhoneFromSp() {
        SharedPreferences sp = BaseApplication.getAppComponent().getSharedPreferences();
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

    void writeAccountToSp(String account) {
        mLoginSp.edit().putString("account", account).apply();
    }

    String readAccountFromSp() {
        return mLoginSp.getString("account", "");
    }
}
