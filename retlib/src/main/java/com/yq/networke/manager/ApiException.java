package com.yq.networke.manager;

/**
 * Created by niejiahuan on 16/8/1.
 */
public class ApiException extends Throwable {
    private String returnCode;
    private String msg;
    public ApiException(String returnCode, String msg){
        this.returnCode=returnCode;
        this.msg=msg;
    }

    public String getReturnCode() {
        return returnCode;
    }

    public String getMsg() {
        return msg;
    }
}
