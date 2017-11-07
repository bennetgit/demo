package com.hong610.domain;

import com.baomidou.mybatisplus.annotations.TableName;
import com.hong610.domain.base.BaseEntity;
import com.hong610.utils.DateUtils;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户拥有的角色
 * Created by Hong on 2016/12/7.
 */
@TableName("user_role")
public class UserRole extends BaseEntity implements Serializable{

	private Long userId;

	private Long roleId;

	private Date createdTime;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	@Override
	public void add() {
		this.setCreatedTime(DateUtils.getSystemDate());
		super.update();
	}
}
