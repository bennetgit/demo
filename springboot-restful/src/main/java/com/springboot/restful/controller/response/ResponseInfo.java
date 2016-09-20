package com.springboot.restful.controller.response;

import java.io.Serializable;

/**
 * Created by feng on 16/9/20.
 */
public class ResponseInfo<T> implements Serializable {

    private static final long serialVersionUID = -8461251819489678982L;

    private Integer status;
    private String msg;
    private T body;

    public ResponseInfo(Integer status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public ResponseInfo(Integer status, String msg, T body) {
        this.status = status;
        this.msg = msg;
        this.body = body;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }
}
