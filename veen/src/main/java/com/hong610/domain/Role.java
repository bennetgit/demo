package com.hong610.domain;

import com.baomidou.mybatisplus.annotations.TableName;
import com.hong610.domain.base.BaseEntity;

import java.io.Serializable;
import java.util.Date;

/**
 * 角色
 * Created by Hong on 2016/11/28.
 */
@TableName("sys_role")
public class Role extends BaseEntity implements Serializable {

    private String name;

    private String nameRemark;

    private Date createdTime;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameRemark() {
        return nameRemark;
    }

    public void setNameRemark(String nameRemark) {
        this.nameRemark = nameRemark;
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
