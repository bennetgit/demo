package com.hong610.domain;

import com.baomidou.mybatisplus.annotations.TableName;
import com.hong610.domain.base.BaseEntity;

import java.io.Serializable;
import java.util.Date;

/**
 * 权限资源
 * Created by Hong on 2016/11/28.
 */
@TableName("sys_resources")
public class Resources extends BaseEntity implements Serializable {

    private String icon;

    private String name;

    private String authority;

    private String url;

    private Integer type;

    private Long parentId;

    private Integer sort;

    private Date createdTime;

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
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
