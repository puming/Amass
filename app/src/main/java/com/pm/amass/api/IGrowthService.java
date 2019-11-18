package com.pm.amass.api;

import androidx.lifecycle.LiveData;

import com.common.retrofit.ApiResponse;
import com.pm.amass.bean.Channel;

import retrofit2.http.GET;

public interface IGrowthService {
    String ARTICLE_TYPE = "/api/article/getarticlecate";
    String ARTICLE_LIST = "/api/article/getarticle";
    String SEARCH_ARTICLE = "/api/article/searcharticle";
    String ARTICLE_DETAIL = "/api/article/getarticledetail";

    @GET(ARTICLE_TYPE)
    LiveData<ApiResponse<Channel>> fetchArticleType();

    @GET(ARTICLE_LIST)
    LiveData<ApiResponse<Object>> fetchArticleList();

    @GET(SEARCH_ARTICLE)
    LiveData<ApiResponse<Object>> searchArticle();

    @GET(ARTICLE_DETAIL)
    LiveData<ApiResponse<Object>> fetchArticleDetail();

}
