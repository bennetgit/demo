package com.hong610.dao.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.hong610.dao.IRoleDao;
import com.hong610.domain.Role;
import com.hong610.mapper.RoleMapper;
import com.hong610.utils.DateUtils;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 角色 Impl
 * Created by Hong on 2016/12/7.
 */
@Component
public class RoleDaoImpl extends ServiceImpl<RoleMapper, Role> implements IRoleDao {

    @Override
    public List<Role> findRoleByUser(Long userId) {
        return baseMapper.findRoleByUser(userId);
    }

    @Override
    public boolean removes(String ids) {
        return retBool(baseMapper.updateSql("update role set is_delete = true, modified_time = '"+ DateUtils.formatNow(DateUtils.YYYYMMDDHHMMSS)+"' where id in ("+ids+")"));
    }

    @Override
    public List<Role> findAll(Page<Role> page, String name) {
        return baseMapper.findAll(page, name);
    }
}
