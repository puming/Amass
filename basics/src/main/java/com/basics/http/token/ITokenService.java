package com.basics.http.token;


import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ITokenService {
    String KEY = "31541eefbdf8e5b8fecb87de02720b05";
    String TOKEN = "/api/index/gettoken";

    @FormUrlEncoded
    @POST(TOKEN)
    Call<Token> requestToken(@Field("key") String key);
}
