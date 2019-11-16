package com.pm.amass.bean;

/**
 * @author pm
 * @date 2019/11/15
 * @email puming@zdsoft.cn
 */
public class Result {

    /**
     * result : 200
     * msg : 登陆成功
     * data :
     */

    private boolean result;
    private String msg;
    private Object data;

    public boolean getResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
