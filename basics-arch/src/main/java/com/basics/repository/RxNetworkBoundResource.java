package com.basics.repository;

import android.util.Log;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;

import com.common.retrofit.ApiResponse;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @author pm
 * @date 2019/1/11
 * @email puming@zdsoft.cn
 */
public abstract class RxNetworkBoundResource<RequestType> {
    private static final String TAG = "RxNetworkBoundResource";


    @MainThread
    public RxNetworkBoundResource() {
        //1初始化NetworkBoundResource
        fetchFromNetwork();
    }

    @NonNull
    @MainThread
    protected abstract Observable<ApiResponse<RequestType>> createCall();

    protected abstract Observer<Resource<RequestType>> observer();

    @MainThread
    protected abstract void onFetchFailed();

    private void fetchFromNetwork() {
        //5进行网络请求
        Observable<ApiResponse<RequestType>> apiResponse = createCall();

        Log.d(TAG, "fetchFromNetwork: ");



        Disposable disposable = apiResponse
                .map(response -> Observable.create((ObservableOnSubscribe<Resource<RequestType>>) emitter -> {
                    if (response instanceof ApiResponse.ApiSuccessResponse) {
                        RequestType body = ((ApiResponse.ApiSuccessResponse<RequestType>) response).getBody();
                        Log.d(TAG, "fetchFromNetwork: body=" + body);
                        emitter.onNext(Resource.success(body));
                    } else if (response instanceof ApiResponse.ApiErrorResponse) {
                        //请求失败使用，传递失败信息
                        ApiResponse.ApiErrorResponse<RequestType> errorResponse = (ApiResponse.ApiErrorResponse<RequestType>) response;
                        String errorMsg = errorResponse.getError();
                        emitter.onNext(Resource.error(errorMsg,errorResponse.getErrorCode()));
                        onFetchFailed();
                    } else if (response instanceof ApiResponse.ApiEmptyResponse) {
                        ApiResponse.ApiEmptyResponse<RequestType> emptyResponse = (ApiResponse.ApiEmptyResponse<RequestType>) response;
                        emitter.onNext(Resource.error());
                        onFetchFailed();
                    }
                    emitter.onComplete();
                }))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(/*resourceObservable ->
                        resourceObservable.subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe()*/);
    }

}
