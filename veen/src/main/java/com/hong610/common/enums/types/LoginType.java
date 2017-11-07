package com.hong610.common.enums.types;

/**
 * 登录类型
 * Created by Hong on 2016/11/28.
 */
public enum LoginType {
    WEB_LOGIN(1, "网页登录"),
    OPENID_LOGIN(2, "OpenId登录");

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

    LoginType(int value, String text) {
        this.value = value;
        this.text = text;
    }
}
