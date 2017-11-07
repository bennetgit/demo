package com.hong610.security.entity;

import com.hong610.domain.Resources;
import com.hong610.domain.Role;
import com.hong610.domain.User;
import com.hong610.security.entity.MyGrantedAuthority;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * SpringSecuroty 自定义用户
 * Created by Hong on 2016/12/7.
 */
public class UserDetail implements UserDetails {

	private long userId;
	
	private String userName;

	private String userPwd;

	private static final long serialVersionUID = 1L;

	private List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

	private List<Role> roles = new ArrayList<Role>();

	private List<Resources> resources = new ArrayList<Resources>();

	public UserDetail(User user, List<Role> roles, List<Resources> resources) {
		this.setUserId(user.getId());
		this.setUserName(user.getUserName());
		this.setUserPwd(user.getUserPwd());
		this.setRoles(roles);
		this.setResources(resources);

		for(Resources res : resources){
			authorities.add(new MyGrantedAuthority(res));
		}
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return this.getUserPwd();
	}

	@Override
	public String getUsername() {
		return this.getUserName();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
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

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public List<Resources> getResources() {
		return resources;
	}

	public void setResources(List<Resources> resources) {
		this.resources = resources;
	}

	@Override
	public String toString() {
		return this.userName;
	}
	
	@Override
	public int hashCode() {
		return userName.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		return this.toString().equals(obj.toString());
	}
}
