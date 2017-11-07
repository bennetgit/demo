package com.hong610.dao.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.hong610.common.enums.Delete;
import com.hong610.dao.IUserRoleDao;
import com.hong610.domain.UserRole;
import com.hong610.mapper.UserRoleMapper;
import com.hong610.utils.DateUtils;
import org.springframework.stereotype.Component;

/**
 * 用户拥有的角色 Impl
 * Created by Hong on 2016/12/7.
 */
@Component
public class UserRoleDaoImpl extends ServiceImpl<UserRoleMapper, UserRole> implements IUserRoleDao {

    @Override
    public boolean removeByUserId(long userId) {
        return retBool(baseMapper.updateSql("update user_role set is_delete = true, modified_time = '"+ DateUtils.formatNow(DateUtils.YYYYMMDDHHMMSS)+"' where user_id = " + userId));
    }

    @Override
    public boolean reDeleteByUserId(long userId, long roleId) {
        UserRole ur = new UserRole();
        ur.setDelete(Delete.UNDELETE.getValue());
        ur.update();
        Wrapper<UserRole> wa = new EntityWrapper<UserRole>();
        wa.where("user_id = " + userId);
        wa.and("role_id = " + roleId);
        return retBool(baseMapper.update(ur, wa));
    }
}
