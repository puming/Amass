package com.pm.amass.api;

import com.common.retrofit.ApiResponse;
import com.pm.amass.bean.Result;
import com.pm.amass.bean.Token;

import androidx.lifecycle.LiveData;
import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * @author pm
 */
public interface ILoginService {
    String TOKEN = "/api/index/gettokenx";
    String SMS_CODE = "/api/index/sendyzm";
    String SIGN_IN = "/api/index/applogin";
    String SIGN_UP = "/api/index/appregister";

    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST(SMS_CODE)
    LiveData<ApiResponse<Result>> requestSmsCode(@Body RequestBody body);

    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST(SIGN_IN)
    LiveData<ApiResponse<Result>> requestSignIn(@Body RequestBody body);

    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST(SIGN_IN)
    LiveData<ApiResponse<Result>> requestSignUp(@Body RequestBody body);

    @FormUrlEncoded
    @POST(TOKEN)
    LiveData<ApiResponse<Token>> requestToken(@Field("key") String key);
}
