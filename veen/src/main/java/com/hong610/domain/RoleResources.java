package com.hong610.domain;

import com.baomidou.mybatisplus.annotations.TableName;
import com.hong610.domain.base.BaseEntity;

import java.io.Serializable;
import java.util.Date;

/**
 * 角色拥有资源
 * Created by Hong on 2016/11/28.
 */
@TableName("role_resources")
public class RoleResources extends BaseEntity implements Serializable {

    private Long roleId;

    private Long resourceId;

    private Date createdTime;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getResourceId() {
        return resourceId;
    }

    public void setResourceId(Long resourceId) {
        this.resourceId = resourceId;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    @Override
    public void add() {
        this.setCreatedTime(new Date());
        super.update();
    }
}
