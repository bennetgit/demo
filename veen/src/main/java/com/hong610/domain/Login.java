package com.hong610.domain;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.hong610.domain.base.BaseEntity;

import java.io.Serializable;
import java.util.Date;

/**
 * 登录日志
 * Created by Hong on 2016/11/28.
 */
@TableName("login")
public class Login extends BaseEntity implements Serializable {

    private Long userId;

    private String userAgent;

    private String ipAddress;

    private Integer type;

    private Date loginTime;

    @TableField(exist = false)
    private Long loginCount;

    public Long getLoginCount() {
        return loginCount;
    }

    public void setLoginCount(Long loginCount) {
        this.loginCount = loginCount;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    @Override
    public void add() {
        this.setLoginTime(new Date());
        super.update();
    }
}
