package com.basics.repository;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import static com.basics.repository.Resource.Status.ERROR;
import static com.basics.repository.Resource.Status.LOADING;
import static com.basics.repository.Resource.Status.MORE_ADD;
import static com.basics.repository.Resource.Status.SUCCEED;

/**
 * @author pm
 * @date 2018/11/26
 * @email puming@zdsoft.cn
 */
public class Resource<T> {

    public enum Status {
        LOADING, MORE_ADD, SUCCEED, ERROR
    }

    public interface Code {
        int OK = 200;
        /**
         * 登录过期的错误码
         */
        int SESSION_TIMEOUT = 707;
        /**
         * 响应但无数据
         */
        int RESPONSE_NO_BODY = 204;
        /**
         * 网络异常或者处理响应时异常
         */
        int EXCEPTION = 0;
        /**
         * 默认值，无效
         */
        int INVALID = -1;

        /**
         * 服务器响应，且有body，但是data为空或success 为false
         */
        int NO_SUCCESS_DATA = 10000;
    }

    @NonNull
    public final Status status;
    @Nullable
    public final T data;
    @Nullable
    public final String message;
    @Nullable
    public final int code;

    private Resource(@NonNull Status status, @Nullable T data, @Nullable String message) {
        this(status, data, message, Code.INVALID);
    }

    private Resource(@NonNull Status status, @Nullable T data, @Nullable String message, int code) {
        this.status = status;
        this.data = data;
        this.message = message;
        this.code = code;
    }

    public static <T> Resource<T> success(@NonNull T data) {
        return new Resource<>(SUCCEED, data, null);
    }

    public static <T> Resource<T> error(String msg, @NonNull T data, int errorCode) {
        return new Resource<>(ERROR, data, msg, errorCode);
    }

    public static <T> Resource<T> error(String msg, int code) {
        return new Resource<>(ERROR, null, msg, code);
    }

    /**
     * 服务器已经响应，但是返回body null
     *
     * @param <T>
     * @return
     */
    public static <T> Resource<T> error() {
        return new Resource<>(ERROR, null, "请求到的数据为空", Code.RESPONSE_NO_BODY);
    }

    public static <T> Resource<T> loading(@Nullable T data) {
        return new Resource<>(LOADING, data, null);
    }

    public static <T> Resource<T> moreSucceed(@Nullable T data) {
        return new Resource<>(MORE_ADD, data, null);
    }

    public static <T> Resource<T> success(@NonNull T data, String msg) {
        return new Resource<>(SUCCEED, data, msg);
    }

    public static <T> Resource<T> loading(@Nullable T data, String msg) {
        return new Resource<>(LOADING, data, msg);
    }

    public static <T> Resource<T> moreSucceed(@Nullable T data, String msg) {
        return new Resource<>(MORE_ADD, data, msg);
    }
}
