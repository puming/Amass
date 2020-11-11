package com.pm.setting;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;

import com.basics.base.BaseViewModel;
import com.basics.repository.LiveNetworkBoundResource;
import com.basics.repository.Resource;
import com.common.retrofit.ApiResponse;
import com.pm.setting.api.ILoginService;
import com.pm.setting.bean.ResultInfo;

/**
 * @author pm
 */
public class SettingViewModel extends BaseViewModel {
    public SettingViewModel(@NonNull Application application) {
        super(application);
    }

    public MediatorLiveData<Resource<ResultInfo>> getLogoutData() {
        LiveNetworkBoundResource<ResultInfo> boundResource = new LiveNetworkBoundResource<ResultInfo>() {
            @NonNull
            @Override
            protected LiveData<ApiResponse<ResultInfo>> createCall() {
                ILoginService loginService = mRetrofitManager.obtainRetrofitService(ILoginService.class);
//                return loginService.requestLogout(AppManager.getInstance().getUid());
                return null;
            }

            @Override
            protected void onFetchFailed() {

            }
        };
        return boundResource.getAsLiveData();
    }
}
