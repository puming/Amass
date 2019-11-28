package com.pm.amass.api;

import com.common.retrofit.ApiResponse;
import com.pm.amass.bean.ChannelResult;
import com.pm.amass.bean.TaskResult;

import androidx.lifecycle.LiveData;

import java.util.Map;

import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * @author pm
 * @date 2019/11/25
 * @email puming@zdsoft.cn
 */
public interface ITaskService {

    String TASK_LIST = "/api/task/gettasklist";
    String TASK_DETAIL = "/api/task/taskdetail";

    @GET(TASK_LIST)
    LiveData<ApiResponse<ChannelResult>> fetchShopType();

    @FormUrlEncoded
    @POST(TASK_LIST)
    LiveData<ApiResponse<TaskResult>> fetchTaskList(@FieldMap Map<String,String> map);
}
