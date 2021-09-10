package com.pm.imine.api;

import androidx.lifecycle.LiveData;

import com.common.retrofit.ApiResponse;
import com.pm.imine.bean.ChannelResult;
import com.pm.imine.bean.ShopResult;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;


/**
 * @author pmcho
 */
public interface ImallService {
    String SHOP_TYPE = "/api/shop/getshopcate";
    String SHOP_LIST = "/api/shop/getshop";
    String SHOP_DETAIL = "/api/shop/getshopdetail";

    @GET(SHOP_TYPE)
    LiveData<ApiResponse<ChannelResult>> fetchShopType();

    @FormUrlEncoded
    @POST(SHOP_LIST)
    LiveData<ApiResponse<ShopResult>> fetchShopList(@Field("cateId") String cateId);

    @GET(SHOP_DETAIL)
    LiveData<ApiResponse<ChannelResult>> fetchShopDetail();
}
