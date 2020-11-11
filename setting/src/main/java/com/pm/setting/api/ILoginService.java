package com.pm.setting.api;

import androidx.lifecycle.LiveData;

import com.common.retrofit.ApiResponse;
import com.pm.setting.bean.ResultInfo;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * @author pm
 */
public interface ILoginService {
    String LOGOUT = "/api/index/applogout";

    @FormUrlEncoded
    @POST(LOGOUT)
    LiveData<ApiResponse<ResultInfo>> requestLogout(@Field("uid") String uid);
}
