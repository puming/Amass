package com.basics.repository;


/**
 * @author pm
 * @date 2019/2/18
 * @email puming@zdsoft.cn
 */
public class Result<D> {


    /**
     * data : null
     * success : true
     * code : null
     * message : null
     */
    private boolean success;
    private String code;
    private String message;
    private D data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public D getData() {
        return data;
    }

    public void setData(D data) {
        this.data = data;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}