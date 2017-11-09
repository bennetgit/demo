package spring.demo.dto.response;

import static spring.demo.constant.Constants.ResponseMsg.FAIL;
import static spring.demo.constant.Constants.ResponseMsg.SUCCESS;

import spring.demo.enums.ResponseCode;

public class ResponseInfo<T> {

    private ResponseCode code;

    private String message;

    private T content;

    public ResponseInfo() {
    }

    ResponseInfo(ResponseCode code, String message, T content) {
        this.code = code;
        this.message = message;
        this.content = content;
    }

    public ResponseCode getCode() {
        return code;
    }

    public void setCode(ResponseCode code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }

    public static <T> ResponseInfo success(T data) {
        return new ResponseInfo(ResponseCode.OK, SUCCESS, data);
    }

    public static ResponseInfo fail() {
        return new ResponseInfo(ResponseCode.FAIL, FAIL, null);
    }
}
