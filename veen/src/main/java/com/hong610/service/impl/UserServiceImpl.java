package com.hong610.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.hong610.common.enums.Delete;
import com.hong610.common.enums.Status;
import com.hong610.common.enums.types.UserType;
import com.hong610.common.exception.AppException;
import com.hong610.common.exception.error.SystemError;
import com.hong610.common.helper.PasswordHelper;
import com.hong610.dao.IUserRoleDao;
import com.hong610.domain.UserRole;
import com.hong610.domain.vo.UserAdmin;
import com.hong610.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hong610.dao.IUserDao;
import com.hong610.domain.User;
import com.hong610.service.IUserService;
import org.springframework.transaction.annotation.Transactional;

/**
 * 用户 Impl
 * Created by Hong on 2016/12/7.
 */
@Service("UserServiceImpl")
public class UserServiceImpl implements IUserService{
	
	@Autowired
	IUserDao userDao;
	@Autowired
	IUserRoleDao userRoleDao;
	
	@Override
	public User findByName(String userName){
		User user = userDao.findByUserName(userName);
		return user;
	}

	@Override
	public Page<UserAdmin> findAdminPage(Page<UserAdmin> page, String nick) {
		page.setRecords(userDao.findAdmin(page, nick));
		return page;
	}

	@Override
	public boolean insertAdmin(String nick, String userName, String password) {
		if(userDao.findByUserName(userName) != null){
			throw new AppException(SystemError.ADMIN_ADD_USER_UNIQUE_ERROR);
		}
		User user = new User();
		user.setNick(nick);
		user.setUserName(userName);
		user.setAdmin(UserType.ADMIN.getValue());
		user.setUserPwd(PasswordHelper.password(userName.concat(password)));
		user.add();
		boolean flag = userDao.insert(user);
		if(!flag){
			throw new AppException(SystemError.ADMIN_ADD_USER_UNIQUE_ERROR);
		}
		return flag;
	}

	@Override
	public boolean updateStatus(Long id, Integer status) {
		User user = new User();
		user.setId(id);
		user.setStatus(status);
		boolean flag = userDao.updateById(user);
		if(!flag){
			throw new AppException(SystemError.ADMIN_EDIT_USER_STATUS_ERROR);
		}
		return flag;
	}

	@Override
	public boolean remove(Long id) {
		User user = new User();
		user.setId(id);
		user.setDelete(Delete.DELETE.getValue());
		boolean flag = userDao.updateById(user);
		if(!flag){
			throw new AppException(SystemError.REMOVE_USER_ERROR);
		}
		return flag;
	}

	@Override
	public boolean removes(String ids) {
		if(StringUtils.isEmpty(ids)){
			throw new AppException(SystemError.REMOVE_NOUSER_ERROR);
		}
		boolean flag = userDao.removes(ids);
		if(!flag){
			throw new AppException(SystemError.REMOVES_USER_ERROR);
		}
		return flag;
	}

	@Override
	public User findById(Long id) {
		User user = userDao.selectById(id);
		if(user == null || user.isDelete() || user.getStatus() != Status.NORMAL.getValue()){
			throw new AppException(SystemError.USER_UNKNOWN_ERROR);
		}
		return user;
	}

	@Override
	public boolean update(User user, String[] roleIds) {
		user.update();
		boolean flag = userDao.updateById(user);
		if(flag){
			flag = userRoleDao.removeByUserId(user.getId());//之前的所有的都删除
			for(String ids : roleIds){
				long id = Long.valueOf(ids);
				UserRole ur = new UserRole();
				ur.setUserId(user.getId());
				ur.setRoleId(id);
				ur.add();
				flag = userRoleDao.insert(ur);
				if(!flag){
					throw new AppException(SystemError.EDIT_USER_ROLE_ERROR);
				}
			}
		}else{
			throw new AppException(SystemError.EDIT_USER_ROLE_ERROR);
		}
		return flag;
	}

}
