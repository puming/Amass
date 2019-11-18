package com.pm.amass.bean;

import com.google.gson.annotations.SerializedName;

public class Channel {
    @SerializedName("result")
    private boolean success;
    private String msg;
    private long id;
    @SerializedName("name")
    private String title;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
