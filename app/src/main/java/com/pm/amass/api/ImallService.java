package com.pm.amass.api;

import androidx.lifecycle.LiveData;

import com.common.retrofit.ApiResponse;
import com.pm.amass.bean.ChannelResult;
import com.pm.amass.bean.ShopResult;

import retrofit2.http.GET;

public interface ImallService {
    String SHOP_TYPE = "/api/shop/getshopcate";
    String SHOP_LIST = "/api/shop/getshop";
    String SHOP_DETAIL = "/api/shop/getshopdetail";

    @GET(SHOP_TYPE)
    LiveData<ApiResponse<ChannelResult>> fetchShopType();

    @GET(SHOP_LIST)
    LiveData<ApiResponse<ShopResult>> fetchShopList();

    @GET(SHOP_DETAIL)
    LiveData<ApiResponse<ChannelResult>> fetchShopDetail();
}
