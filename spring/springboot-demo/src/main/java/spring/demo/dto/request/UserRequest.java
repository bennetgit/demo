package spring.demo.dto.request;

import org.joda.time.LocalDateTime;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import spring.demo.enums.SexType;
import spring.demo.util.JsonEnumDeserializer;

public class UserRequest extends BaseRequest {

    private String username;

    private String mobile;

    @JsonDeserialize(using = JsonEnumDeserializer.class)
    private SexType sex;

    private LocalDateTime createdOn;

    private LocalDateTime updatedOn;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }

    public LocalDateTime getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(LocalDateTime updatedOn) {
        this.updatedOn = updatedOn;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public SexType getSex() {
        return sex;
    }

    public void setSex(SexType sex) {
        this.sex = sex;
    }
}
