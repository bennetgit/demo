package spring.demo.dto.response;

import static spring.demo.constant.Constants.ResponseMsg.FAIL;
import static spring.demo.constant.Constants.ResponseMsg.SUCCESS;

import spring.demo.ResponseCode;

public class ResponseInfo<T> {

    private ResponseCode code;

    private String msg;

    private T body;

    public ResponseInfo() {
    }

    public ResponseInfo(ResponseCode code, String msg, T body) {
        this.code = code;
        this.msg = msg;
        this.body = body;
    }

    public ResponseCode getCode() {
        return code;
    }

    public void setCode(ResponseCode code) {
        this.code = code;
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

    public static <T> ResponseInfo success(T data) {
        return new ResponseInfo(ResponseCode.OK, SUCCESS, data);
    }

    public static ResponseInfo fail() {
        return new ResponseInfo(ResponseCode.FAIL, FAIL, null);
    }
}
