package com.pm.amass.api;

import androidx.lifecycle.LiveData;

import com.basics.repository.Result;
import com.common.retrofit.ApiResponse;
import com.pm.amass.bean.ArticleDetailsResult;
import com.pm.amass.bean.ArticleResult;
import com.pm.amass.bean.ChannelResult;

import java.util.Map;

import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * @author pmcho
 */
public interface IGrowthService {
    String ARTICLE_TYPE = "/api/article/getarticlecate";
    String ARTICLE_LIST = "/api/article/getarticle";
    String SEARCH_ARTICLE = "/api/article/searcharticle";
    String ARTICLE_DETAIL = "/api/article/getarticledetail";

    @GET(ARTICLE_TYPE)
    LiveData<ApiResponse<ChannelResult>> fetchArticleType();

    @FormUrlEncoded
    @POST(ARTICLE_LIST)
    LiveData<ApiResponse<ArticleResult>> fetchArticleList(@Field("cateId") String cateId);

    @GET(SEARCH_ARTICLE)
    LiveData<ApiResponse<Object>> searchArticle();

    @FormUrlEncoded
    @POST(ARTICLE_DETAIL)
    LiveData<ApiResponse<ArticleDetailsResult>> fetchArticleDetail(@FieldMap Map<String,String> map);

}
