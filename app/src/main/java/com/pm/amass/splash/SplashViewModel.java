package com.pm.amass.splash;

import android.app.Application;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;

import com.basics.base.BaseViewModel;
import com.basics.repository.LiveNetworkBoundResource;
import com.basics.repository.Resource;
import com.common.retrofit.ApiResponse;
import com.pm.amass.MainApplication;
import com.pm.amass.api.ILoginService;
import com.pm.amass.bean.Token;

import java.util.Map;

/**
 * @author pmcho
 */
public class SplashViewModel extends BaseViewModel {
    private static final String TAG = "SplashViewModel";
    private MediatorLiveData<Boolean> mTokenData = new MediatorLiveData<Boolean>();
    private MediatorLiveData<Boolean> mLoginStateData = new MediatorLiveData<Boolean>();
    private MediatorLiveData<Resource<Token>> resource;
    private MediatorLiveData<Resource<Map<String, Object>>> checkTokenState;

    public SplashViewModel(@NonNull Application application) {
        super(application);
    }

    /*public LiveData<Boolean> getToken() {
        resource = new LiveNetworkBoundResource<Token>() {
            @NonNull
            @Override
            protected LiveData<ApiResponse<Token>> createCall() {
                ILoginService loginService = mRetrofitManager.obtainRetrofitService(ILoginService.class);
                return loginService.requestToken(ILoginService.KEY);
            }

            @Override
            protected void onFetchFailed() {

            }
        }.getAsLiveData();
        mTokenData.addSource(resource, tokenResource -> {
            switch (tokenResource.status) {
                case SUCCEED:
                    Token token = tokenResource.data;
                    if (token == null) {
                        mTokenData.setValue(false);
                        break;
                    }
                    Token.DataBean tokenData = token.getData();
                    if (tokenData == null) {
                        mTokenData.setValue(false);
                        break;
                    }
                    Token.DataBean.TokenBean tokenBean = tokenData.getToken();
                    if (tokenBean == null) {
                        mTokenData.setValue(false);
                        break;
                    }
                    String tokenString = tokenBean.getInfo();
                    boolean cacheOk = mAppComponent.getLoginSharedPreferences()
                            .edit()
                            .putString("token", tokenString)
                            .commit();
                    mTokenData.setValue(true);
                    break;
                case ERROR:
                    mTokenData.setValue(false);
                    break;
                default:
                    break;
            }
        });
        return mTokenData;
    }*/

    public LiveData<Boolean> getLoginState() {
        String cacheToken = readTokenFromSp();
        if (TextUtils.isEmpty(cacheToken)) {
            mLoginStateData.setValue(false);
            return mLoginStateData;
        }
        checkTokenState = new LiveNetworkBoundResource<Map<String, Object>>() {
            @NonNull
            @Override
            protected LiveData<ApiResponse<Map<String, Object>>> createCall() {
                ILoginService loginService = mRetrofitManager.obtainRetrofitService(ILoginService.class);
                return loginService.requestCheckToken(cacheToken);
            }

            @Override
            protected void onFetchFailed() {

            }
        }.getAsLiveData();
        mLoginStateData.addSource(checkTokenState, tokenResource -> {
            int code = tokenResource.code;
            Log.d(TAG, "getLoginState: code=" + code);
            switch (tokenResource.status) {
                case SUCCEED:
                    Map<String, Object> map = tokenResource.data;
                    Log.d(TAG, "getLoginState: " + map);
                    if (map == null) {
                        mTokenData.setValue(false);
                        break;
                    }
                    mTokenData.setValue(true);
                    break;
                case ERROR:
                    if(tokenResource.code == 707){
                        mTokenData.setValue(false);
                    }
                    break;
                default:
                    break;
            }
        });
        return mLoginStateData;
    }

    void writeTokenToSp(String token) {
        SharedPreferences sp = mAppComponent.getLoginSharedPreferences();
        sp.edit().putString("token", token).apply();
    }

    String readTokenFromSp() {
        SharedPreferences sp = MainApplication.getAppComponent().getLoginSharedPreferences();
        return sp.getString("token", "");
    }


    @Override
    protected void onCleared() {
        super.onCleared();
        mTokenData.removeSource(resource);
    }
}
