package com.pm.amass.api;

import com.common.retrofit.ApiResponse;
import com.pm.amass.bean.ChannelResult;

import androidx.lifecycle.LiveData;

import retrofit2.http.GET;

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

    @GET(TASK_LIST)
    LiveData<ApiResponse<ChannelResult>> fetchShopList();
}
