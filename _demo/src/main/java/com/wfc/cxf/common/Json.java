package com.wfc.cxf.common;

import java.io.Serializable;

public abstract class Json implements Serializable {

    private static final long serialVersionUID = 5039316167035640124L;

    protected boolean flag = true;

    protected String msg = "";

    protected Object data;

    public Json() {
        super();
    }

    public Json(boolean flag, String msg, Object data) {
        super();
        this.flag = flag;
        this.msg = msg;
        this.data = data;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

}
