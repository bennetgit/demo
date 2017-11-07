package com.hong610.common.exception;

/**
 * 自定义异常
 * Created by Hong on 2016/12/4.
 */
public class AppException extends RuntimeException {

    private String code;

    public AppException(String code, String message){
        super(message);
        this.setCode(code);
    }

    public AppException(Error error){
        super(error.getMessage());
        this.setCode(error.getCode());
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
