package com.basics.repository;

import android.util.Log;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Transformations;

import com.common.retrofit.ApiResponse;

/**
 * @author pm
 * @date 2018/11/26
 * @email puming@zdsoft.cn
 */
public abstract class LiveNetworkBoundResource<RequestType> {
    private static final String TAG = "LiveBoundResource";
    private final MediatorLiveData<Resource<RequestType>> result = new MediatorLiveData<>();

    @NonNull
    @MainThread
    protected abstract LiveData<ApiResponse<RequestType>> createCall();


    @MainThread
    protected abstract void onFetchFailed();

    @MainThread
    public LiveNetworkBoundResource() {
        //1初始化NetworkBoundResource
        result.setValue(Resource.loading(null));
        //2从网络加载数据
        fetchFromNetwork();
    }

    private void fetchFromNetwork() {
        //进行网络请求
        LiveData<ApiResponse<RequestType>> apiResponse = createCall();
        // we re-attach dbSource as a new source,
        // it will dispatch its latest value quickly
        LiveData<Resource<RequestType>> mapData = Transformations.map(apiResponse, response -> {
            Log.d(TAG, "fetchFromNetwork: apiResponse :" + response);
            if (response instanceof ApiResponse.ApiSuccessResponse) {
                ApiResponse.ApiSuccessResponse<RequestType> successResponse = (ApiResponse.ApiSuccessResponse<RequestType>) response;
                RequestType body = successResponse.getBody();
                if (body instanceof Result) {
                    Result result = (Result) body;
                    boolean success = result.isSuccess();
                    if(success){
                        return Resource.success(body);
                    }else {
                        return Resource.error(result.getMessage(),body,Resource.Code.NO_SUCCESS_DATA);
                    }
                } else {
                    return Resource.success(body);
                }
//                return Resource.success(body);
            } else if (response instanceof ApiResponse.ApiErrorResponse) {
                //请求失败使用，传递失败信息
                ApiResponse.ApiErrorResponse<RequestType> errorResponse = (ApiResponse.ApiErrorResponse<RequestType>) response;
                String errorMsg = errorResponse.getError();
                int errorCode = errorResponse.getErrorCode();
                onFetchFailed();
                return Resource.error(errorMsg, errorCode);
            } else if (response instanceof ApiResponse.ApiEmptyResponse) {
                return Resource.error();
            }
            return Resource.error();
        });

        result.addSource(mapData, newData -> {
            Log.d(TAG, "fetchFromNetwork: mapData changed");
            result.removeSource(mapData);
            result.setValue(newData);
        });

        /*result.addSource(apiResponse, response -> {
            result.removeSource(apiResponse);
            //noinspection ConstantConditions
            Log.d(TAG, "fetchFromNetwork: apiResponse changed" + response);
            if (response instanceof ApiResponse.ApiSuccessResponse) {
                ApiResponse.ApiSuccessResponse<RequestType> successResponse = (ApiResponse.ApiSuccessResponse<RequestType>) response;
               *//* result.addSource(apiResponse,
                        newData -> result.setValue(Resource.success(successResponse.getBody())));*//*
                Log.d(TAG, "fetchFromNetwork: Success");
               result.setValue(Resource.success(successResponse.getBody()));
            } else if (response instanceof ApiResponse.ApiErrorResponse) {
                //请求失败使用，传递失败信息
                String errorMsg = ((ApiResponse.ApiErrorResponse<RequestType>) response).getError();
                onFetchFailed();
                result.addSource(apiResponse,
                        newData -> result.setValue(Resource.error(errorMsg)));
            } else if (response instanceof ApiResponse.ApiEmptyResponse) {
                result.addSource(apiResponse,
                        newData -> result.setValue(Resource.error()));
            }
        });*/
    }

    public final MediatorLiveData<Resource<RequestType>> getAsLiveData() {
        return result;
    }
}
