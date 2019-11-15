package com.pm.amass.login;

import android.app.Application;

import com.basics.base.BaseViewModel;
import com.basics.repository.LiveNetworkBoundResource;
import com.basics.repository.Resource;
import com.common.retrofit.ApiResponse;
import com.common.retrofit.IRetrofitManager;
import com.pm.amass.MainApplication;
import com.pm.amass.api.ILoginService;
import com.pm.amass.bean.Token;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;

/**
 * @author pm
 */
public class SignInViewModel extends BaseViewModel {

    private final IRetrofitManager retrofitManager;

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
                return loginService.requestToken("31541eefbdf8e5b8fecb87de02720b05");
            }

            @Override
            protected void onFetchFailed() {

            }
        };
        return boundResource.getAsLiveData();
    }
}
