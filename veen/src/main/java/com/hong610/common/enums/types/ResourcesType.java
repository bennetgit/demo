package com.hong610.common.enums.types;

/**
 * 登录类型
 * Created by Hong on 2016/11/28.
 */
public enum ResourcesType {
    TOPMENU(0, "权限用途"),
    ONEMENU(1, "一级菜单"),
    TWOMENU(2, "二级菜单"),
    LINK(3, "链接");

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

    ResourcesType(int value, String text) {
        this.value = value;
        this.text = text;
    }
}
