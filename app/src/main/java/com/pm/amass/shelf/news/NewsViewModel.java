package com.pm.amass.shelf.news;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.basics.base.BaseViewModel;
import com.basics.repository.LiveNetworkBoundResource;
import com.basics.repository.Resource;
import com.common.retrofit.ApiResponse;
import com.pm.amass.api.INewService;
import com.pm.amass.bean.ArticleResult;
import com.pm.amass.bean.NewResult;
import com.pm.amass.manager.AppManager;

import java.util.HashMap;

/**
 * @author pmcho
 */
public class NewsViewModel extends BaseViewModel {


    public NewsViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<Resource<NewResult>> getNewList() {
        return new LiveNetworkBoundResource<NewResult>() {
            @NonNull
            @Override
            protected LiveData<ApiResponse<NewResult>> createCall() {
                INewService newService = mRetrofitManager.obtainRetrofitService(INewService.class);
                String token = AppManager.getInstance().getToken();
                String uid = AppManager.getInstance().getUid();
                HashMap<String, String> map = new HashMap<>(2);
                map.put("token", token);
                map.put("uid", uid);
                return newService.getNewList(map);
            }

            @Override
            protected void onFetchFailed() {

            }
        }.getAsLiveData();
    }
}