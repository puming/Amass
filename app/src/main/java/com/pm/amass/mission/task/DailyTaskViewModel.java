package com.pm.amass.mission.task;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.basics.base.BaseViewModel;
import com.basics.repository.LiveNetworkBoundResource;
import com.basics.repository.Resource;
import com.common.retrofit.ApiResponse;
import com.pm.amass.api.ITaskService;
import com.pm.amass.bean.TaskResult;
import com.pm.amass.manager.AppManager;

import java.util.HashMap;

/**
 * @author pmcho
 */
public class DailyTaskViewModel extends BaseViewModel {
    public DailyTaskViewModel(@NonNull Application application) {
        super(application);
    }


    public LiveData<Resource<TaskResult>> getTaskListData(String type) {
        return new LiveNetworkBoundResource<TaskResult>() {
            @NonNull
            @Override
            protected LiveData<ApiResponse<TaskResult>> createCall() {
                HashMap<String, String> map = new HashMap<>(2);
                map.put("uid", AppManager.getInstance().getUid());
                map.put("type", type);
                ITaskService taskService = mRetrofitManager.obtainRetrofitService(ITaskService.class);
                return taskService.fetchTaskList(map);
            }

            @Override
            protected void onFetchFailed() {

            }
        }.getAsLiveData();
    }
}
