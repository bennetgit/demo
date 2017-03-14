package spring.demo;

import com.fasterxml.jackson.annotation.JsonValue;

public enum ResponseCode {

    OK("1000"), FAIL("10001");
    private String code;

    private ResponseCode(String code) {
        this.code = code;
    }

    @JsonValue
    public String getCode() {
        return code;
    }
}
