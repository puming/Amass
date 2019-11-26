package com.pm.amass.mine.setting;

import android.app.Application;

import com.basics.base.BaseViewModel;
import com.basics.repository.LiveNetworkBoundResource;
import com.basics.repository.Resource;
import com.common.retrofit.ApiResponse;
import com.pm.amass.api.ILoginService;
import com.pm.amass.bean.ResultInfo;
import com.pm.amass.manager.AppManager;

import java.util.Map;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.ViewModel;

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
                return loginService.requestLogout(AppManager.getInstance().getUid());
            }

            @Override
            protected void onFetchFailed() {

            }
        };
        return boundResource.getAsLiveData();
    }
}
