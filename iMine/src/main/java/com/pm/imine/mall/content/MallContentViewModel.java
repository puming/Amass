package com.pm.imine.mall.content;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.basics.base.BaseViewModel;
import com.basics.repository.LiveNetworkBoundResource;
import com.basics.repository.Resource;
import com.common.retrofit.ApiResponse;
import com.pm.imine.api.ImallService;
import com.pm.imine.bean.ChannelResult;
import com.pm.imine.bean.ShopResult;

/**
 * @author pmcho
 */
public class MallContentViewModel extends BaseViewModel {
    public MallContentViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<Resource<ChannelResult>> getShopType(){
        return new LiveNetworkBoundResource<ChannelResult>() {
            @NonNull
            @Override
            protected LiveData<ApiResponse<ChannelResult>> createCall() {
                ImallService imallService = mRetrofitManager.obtainRetrofitService(ImallService.class);
                return imallService.fetchShopType();
            }
            @Override
            protected void onFetchFailed() {
            }
        }.getAsLiveData();
    }

    public LiveData<Resource<ShopResult>> getShopListData(String cateId){
        return new LiveNetworkBoundResource<ShopResult>() {
            @NonNull
            @Override
            protected LiveData<ApiResponse<ShopResult>> createCall() {
                ImallService imallService = mRetrofitManager.obtainRetrofitService(ImallService.class);
                return imallService.fetchShopList(cateId);
            }
            @Override
            protected void onFetchFailed() {
            }
        }.getAsLiveData();
    }
}
