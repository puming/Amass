package com.pm.amass.api;

import androidx.lifecycle.LiveData;

import com.common.retrofit.ApiResponse;
import com.pm.amass.bean.NewResult;

import java.util.Map;

import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * @author pmcho
 */
public interface INewService {
    String NEWS_LIST = "/api/news/getnewslist";
    @FormUrlEncoded
    @POST(NEWS_LIST)
    LiveData<ApiResponse<NewResult>> getNewList(@FieldMap Map<String, String> map);
}
