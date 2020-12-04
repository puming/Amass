package com.pm.imain.api;

import androidx.lifecycle.LiveData;

import com.basics.repository.Result;
import com.common.retrofit.ApiResponse;
import com.pm.imain.bean.ResultInfo;
import com.pm.imain.bean.Token;
import com.pm.imain.bean.UserResult;

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
    String LOGOUT = "/api/index/applogout";
    String SIGN_UP = "/api/index/appregister";

    @FormUrlEncoded
    @POST(SMS_CODE)
    LiveData<ApiResponse<ResultInfo>> requestSmsCode(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST(SIGN_IN)
    LiveData<ApiResponse<UserResult>> requestSignIn(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST(LOGOUT)
    LiveData<ApiResponse<ResultInfo>> requestLogout(@Field("uid") String uid);

    @FormUrlEncoded
    @POST(SIGN_UP)
    LiveData<ApiResponse<ResultInfo>> requestSignUp(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST(TOKEN)
    LiveData<ApiResponse<Result<Token>>> requestToken(@Field("key") String key);

    @FormUrlEncoded
    @POST(CHECK)
    LiveData<ApiResponse<Map<String, Object>>> requestCheckToken(@Field("token") String token);
}
