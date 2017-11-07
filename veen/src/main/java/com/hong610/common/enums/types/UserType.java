package com.hong610.common.enums.types;

/**
 * 登录类型
 * Created by Hong on 2016/11/28.
 */
public enum UserType {
    USER(1, "普通会员"),
    ADMIN(2, "管理员");

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

    UserType(int value, String text) {
        this.value = value;
        this.text = text;
    }
}
