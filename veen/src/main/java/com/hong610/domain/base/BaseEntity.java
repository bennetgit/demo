package com.hong610.domain.base;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import com.hong610.common.enums.Delete;
import com.hong610.utils.DateUtils;

import java.io.Serializable;
import java.util.Date;

/**
 * 实体类的基类 Created by Hong on 2016/11/28.
 */
public class BaseEntity implements Serializable {

    @TableId(type = IdType.ID_WORKER)
    private Long id;

    private Date modifiedTime;

    private Integer status;

    private int isDelete;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 修改时间
     */
    public Date getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(Date modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    /**
     * 状态 1：正常 2：非正常
     */
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public int getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(int isDelete) {
        this.isDelete = isDelete;
    }

    /**
     * 是否删除 false：未删除 true：删除
     */
    public boolean isDelete() {
        return isDelete == Delete.DELETE.getValue();
    }

    public void setDelete(int delete) {
        isDelete = delete;
    }

    /**
     * 设置ModifiedTime的值
     */
    public void update() {
        this.setModifiedTime(DateUtils.getSystemDate());
    }

    /**
     * 设置CreatedTime
     */
    public void add() {
    }
}
