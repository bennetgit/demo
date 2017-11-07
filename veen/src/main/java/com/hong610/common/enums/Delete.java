package com.hong610.common.enums;

/**
 * 删除 Created by Hong on 2016/11/28.
 */
public enum Delete {
    UNDELETE(0, "未删除"), DELETE(1, "删除");

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

    Delete(int value, String text) {
        this.value = value;
        this.text = text;
    }
}
