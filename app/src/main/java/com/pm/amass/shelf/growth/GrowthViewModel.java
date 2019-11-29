package com.pm.amass.shelf.growth;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.basics.base.BaseViewModel;
import com.basics.repository.LiveNetworkBoundResource;
import com.basics.repository.Resource;
import com.basics.repository.Result;
import com.common.retrofit.ApiResponse;
import com.pm.amass.api.IGrowthService;
import com.pm.amass.bean.ArticleDetailsResult;
import com.pm.amass.bean.ArticleResult;
import com.pm.amass.bean.ChannelResult;
import com.pm.amass.manager.AppManager;

import java.util.HashMap;
import java.util.Map;

/**
 * @author pmcho
 */
public class GrowthViewModel extends BaseViewModel {

    private MutableLiveData<String> mText;
    private final IGrowthService mGrowthService;

    public GrowthViewModel(@NonNull Application application) {
        super(application);
        mText = new MutableLiveData<>();
        mText.setValue("This is dashboard fragment");
        mGrowthService = mRetrofitManager.obtainRetrofitService(IGrowthService.class);
    }

    public LiveData<String> getText() {
        return mText;
    }

    public LiveData<Resource<ChannelResult>> getArticleChannel() {
        return new LiveNetworkBoundResource<ChannelResult>() {
            @NonNull
            @Override
            protected LiveData<ApiResponse<ChannelResult>> createCall() {
                return mGrowthService.fetchArticleType();
            }

            @Override
            protected void onFetchFailed() {

            }
        }.getAsLiveData();
    }

    public LiveData<Resource<ArticleResult>> getArticleList(String cateId) {
        return new LiveNetworkBoundResource<ArticleResult>() {
            @NonNull
            @Override
            protected LiveData<ApiResponse<ArticleResult>> createCall() {
                return mGrowthService.fetchArticleList(cateId);
            }

            @Override
            protected void onFetchFailed() {

            }
        }.getAsLiveData();
    }

    public LiveData<Resource<ArticleDetailsResult>> getArticleDetails(String articleId) {
        return new LiveNetworkBoundResource<ArticleDetailsResult>() {
            @NonNull
            @Override
            protected LiveData<ApiResponse<ArticleDetailsResult>> createCall() {
                HashMap<String, String> map = new HashMap<>(2);
                map.put("articleId", articleId);
                map.put("UserId", AppManager.getInstance().getUid());
                return mGrowthService.fetchArticleDetail(map);
            }

            @Override
            protected void onFetchFailed() {

            }
        }.getAsLiveData();
    }

    public LiveData<Resource<Object>> getSearchArticle() {
        return new LiveNetworkBoundResource<Object>() {
            @NonNull
            @Override
            protected LiveData<ApiResponse<Object>> createCall() {
                return mGrowthService.searchArticle();
            }

            @Override
            protected void onFetchFailed() {

            }
        }.getAsLiveData();
    }

    public LiveData<Resource<Result<String>>> getActionData(String articleId, String action) {
        return new LiveNetworkBoundResource<Result<String>>() {
            @NonNull
            @Override
            protected LiveData<ApiResponse<Result<String>>> createCall() {
                HashMap<String, String> map = new HashMap<>(3);
                map.put("articleId", articleId);
                map.put("UserId", AppManager.getInstance().getUid());
                map.put("action", action);
                return mGrowthService.fetchArticleAction(map);
            }

            @Override
            protected void onFetchFailed() {

            }
        }.getAsLiveData();
    }

    public LiveData<Resource<Map<String,String>>> getCommentData(String articleId, String content) {
        return new LiveNetworkBoundResource<Map<String,String>>() {
            @NonNull
            @Override
            protected LiveData<ApiResponse<Map<String,String>>> createCall() {
                HashMap<String, String> map = new HashMap<>(3);
                map.put("articleId", articleId);
                map.put("UserId", AppManager.getInstance().getUid());
                map.put("content", content);
                return mGrowthService.fetchArticleComment(map);
            }

            @Override
            protected void onFetchFailed() {

            }
        }.getAsLiveData();
    }
}