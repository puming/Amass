package com.pm.amass.ui.growth;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.basics.base.BaseViewModel;
import com.basics.repository.LiveNetworkBoundResource;
import com.basics.repository.Resource;
import com.common.retrofit.ApiResponse;
import com.pm.amass.api.IGrowthService;
import com.pm.amass.bean.Channel;

/**
 * @author pmcho
 */
public class GrowthViewModel extends BaseViewModel {

    private MutableLiveData<String> mText;
    private final IGrowthService mGrowthService;

    public GrowthViewModel(@NonNull Application application) {
        super(application);
        mText = new MutableLiveData<>();
        mText.setValue("This is dashboard fragment");
        mGrowthService = mRetrofitManager.obtainRetrofitService(IGrowthService.class);
    }

    public LiveData<String> getText() {
        return mText;
    }

    public LiveData<Resource<Channel>> getArticleChannel() {
        return new LiveNetworkBoundResource<Channel>() {
            @NonNull
            @Override
            protected LiveData<ApiResponse<Channel>> createCall() {
                return mGrowthService.fetchArticleType();
            }

            @Override
            protected void onFetchFailed() {

            }
        }.getAsLiveData();
    }

    public LiveData<Resource<Object>> getArticleList() {
        return new LiveNetworkBoundResource<Object>() {
            @NonNull
            @Override
            protected LiveData<ApiResponse<Object>> createCall() {
                return mGrowthService.fetchArticleList();
            }

            @Override
            protected void onFetchFailed() {

            }
        }.getAsLiveData();
    }

    public LiveData<Resource<Object>> getSearchArticle() {
        return new LiveNetworkBoundResource<Object>() {
            @NonNull
            @Override
            protected LiveData<ApiResponse<Object>> createCall() {
                return mGrowthService.searchArticle();
            }

            @Override
            protected void onFetchFailed() {

            }
        }.getAsLiveData();
    }
}