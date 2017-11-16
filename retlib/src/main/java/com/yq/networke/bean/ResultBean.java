package com.yq.networke.bean;

/**
 * Created by niejiahuan on 16/8/1.
 */
public class ResultBean<T> {


    /**
     * status : 0
     * message : 登录成功
     * result : {"user":{"phone":"18201893498","user_id":"18201893498","uid":"239","nickname":"18201893498","token":"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiIxODIwMTg5MzQ5ODYzZWU0NTE5MzllZDU4MGVmM2M0YjZmMDEwOWQxZmQwIiwiZXhwIjoxNDc4NTMxMDM0fQ.SXOQULw45V_4HFkZ4aWSrNT-ooy2zWfEFHScEXAoAFw","headurl":"images/header/icon_woman-720p.png","balance":0,"restertime":"2016-10-31"}}
     */

    private int status;
    private String message;
    /**
     * user : {"phone":"18201893498","user_id":"18201893498","uid":"239","nickname":"18201893498","token":"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiIxODIwMTg5MzQ5ODYzZWU0NTE5MzllZDU4MGVmM2M0YjZmMDEwOWQxZmQwIiwiZXhwIjoxNDc4NTMxMDM0fQ.SXOQULw45V_4HFkZ4aWSrNT-ooy2zWfEFHScEXAoAFw","headurl":"images/header/icon_woman-720p.png","balance":0,"restertime":"2016-10-31"}
     */

    private T result;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }


}
