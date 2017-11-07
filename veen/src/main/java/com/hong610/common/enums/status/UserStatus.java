package com.hong610.common.enums.status;

import com.hong610.common.enums.Status;

/**
 * 用户状态
 * Created by Hong on 2016/12/14.
 */
public enum UserStatus {

    ENABLED(1, "正常"),
    DISABLED(2, "禁用");

    private int value;

    private String text;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    UserStatus(int value, String text) {
        this.value = value;
        this.text = text;
    }
}
