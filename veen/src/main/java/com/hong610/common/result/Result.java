package com.hong610.common.result;

import com.hong610.common.exception.Error;

/**
 * 返回结果集
 * Created by Hong on 2016/12/7.
 */
public class Result<T> extends Error {

    private boolean success = false;

    private T value = null;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
}
