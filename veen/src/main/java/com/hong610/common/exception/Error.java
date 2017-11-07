package com.hong610.common.exception;

/**
 * 异常信息基类
 * Created by Hong on 2016/12/4.
 */
public class Error {
    private boolean success = false;
    private String code;
    private String message;

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

    public void setMessage(String message) {
        this.message = message;
    }

    public Error() {}
    public Error(String code, String message){
        this.code = code;
        this.message = message;
    }
}
