package com.hong610.domain;

import com.baomidou.mybatisplus.annotations.TableName;
import com.hong610.domain.base.BaseEntity;

import java.io.Serializable;
import java.util.Date;
/**
 * 用户
 * Created by Hong on 2016/12/7.
 */
@TableName("sys_user")
public class User extends BaseEntity implements Serializable{

	private String nick;
	
	private String userName;

	private String userPwd;

	private Integer admin;

	private Date registerTime;

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	public Integer getAdmin() {
		return admin;
	}

	public void setAdmin(Integer admin) {
		this.admin = admin;
	}

	public Date getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(Date registerTime) {
		this.registerTime = registerTime;
	}

	@Override
	public void add() {
		this.setRegisterTime(new Date());
		super.update();
	}

}
