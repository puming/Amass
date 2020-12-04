package com.pm.imain.mission.task.details;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.basics.base.BaseViewModel;
import com.basics.repository.LiveNetworkBoundResource;
import com.basics.repository.Resource;
import com.basics.repository.Result;
import com.common.retrofit.ApiResponse;
import com.pm.imain.api.ITaskService;
import com.pm.imain.bean.TaskDetailsResult;
import com.pm.imain.manager.AppManager;

import java.util.HashMap;

/**
 * @author pmcho
 */
public class TaskDetailsViewModel extends BaseViewModel {

    private String tid;

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public TaskDetailsViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<Resource<TaskDetailsResult>> getTaskDetailsData() {
        return new LiveNetworkBoundResource<TaskDetailsResult>() {
            @NonNull
            @Override
            protected LiveData<ApiResponse<TaskDetailsResult>> createCall() {
                ITaskService taskService = mRetrofitManager.obtainRetrofitService(ITaskService.class);
                return taskService.fetchTaskDetails(getTid());
            }

            @Override
            protected void onFetchFailed() {

            }
        }.getAsLiveData();
    }

    public LiveData<Resource<Result<String>>> getSubmitData() {
        return new LiveNetworkBoundResource<Result<String>>() {
            @NonNull
            @Override
            protected LiveData<ApiResponse<Result<String>>> createCall() {
                ITaskService taskService = mRetrofitManager.obtainRetrofitService(ITaskService.class);
                HashMap<String, String> map = new HashMap<>(10);
                map.put("uid",AppManager.getInstance().getUid());
                map.put("tid","");
                map.put("desc","");
                map.put("type","");
                map.put("media","");
                map.put("is_syn","");
                map.put("address","");
                return taskService.submitTask(map);
            }

            @Override
            protected void onFetchFailed() {

            }
        }.getAsLiveData();
    }
}
