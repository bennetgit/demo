package com.hong610.common.enums;

/**
 * 基本状态
 * Created by Hong on 2016/11/28.
 */
public enum Status {
    NORMAL(1, "正常"),
    UNNORMAL(2, "非正常");


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

    Status(int value, String text) {
        this.value = value;
        this.text = text;
    }
}
