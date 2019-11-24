package com.pm.amass.api;

import com.basics.repository.Result;
import com.common.retrofit.ApiResponse;
import com.pm.amass.bean.ResultInfo;
import com.pm.amass.bean.Token;

import androidx.lifecycle.LiveData;

import java.util.Map;

import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * @author pm
 */
public interface ILoginService {
    String KEY = "31541eefbdf8e5b8fecb87de02720b05";
    String TOKEN = "/api/index/gettoken";
    String CHECK = "/api/index/tokencheck";
    String SMS_CODE = "/api/index/sendyzm";
    String SIGN_IN = "/api/index/applogin";
    String SIGN_UP = "/api/index/appregister";

    @FormUrlEncoded
    @POST(SMS_CODE)
    LiveData<ApiResponse<ResultInfo>> requestSmsCode(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST(SIGN_IN)
    LiveData<ApiResponse<ResultInfo>> requestSignIn(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST(SIGN_UP)
    LiveData<ApiResponse<ResultInfo>> requestSignUp(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST(TOKEN)
    LiveData<ApiResponse<Result<Token>>> requestToken(@Field("key") String key);

    @FormUrlEncoded
    @POST(CHECK)
    LiveData<ApiResponse<Map<String,Object>>> requestCheckToken(@Field("token") String token);
}
