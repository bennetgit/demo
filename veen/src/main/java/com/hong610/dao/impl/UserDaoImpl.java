package com.hong610.dao.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.hong610.common.enums.types.UserType;
import com.hong610.dao.IUserDao;
import com.hong610.domain.User;
import com.hong610.domain.vo.UserAdmin;
import com.hong610.mapper.UserMapper;
import com.hong610.utils.DateUtils;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 用户 Impl
 * Created by Hong on 2016/12/7.
 */
@Component
public class UserDaoImpl extends ServiceImpl<UserMapper, User> implements IUserDao{

	@Override
	public User findByUserName(String userName) {
		User user = new User();
		user.setUserName(userName);
		return baseMapper.selectOne(user);
	}

	@Override
	public List<UserAdmin> findAdmin(Page<UserAdmin> page, String nick) {
		return baseMapper.findAdmin(page, UserType.ADMIN.getValue(), nick);
	}

	@Override
	public boolean removes(String ids) {
		return retBool(baseMapper.updateSql("update user set is_delete = true, modified_time = '"+ DateUtils.formatNow(DateUtils.YYYYMMDDHHMMSS)+"' where id in ("+ids+")"));
	}

}
