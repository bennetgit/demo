package com.hong610.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.hong610.common.enums.Delete;
import com.hong610.common.enums.Status;
import com.hong610.common.exception.AppException;
import com.hong610.common.exception.error.SystemError;
import com.hong610.dao.IRoleDao;
import com.hong610.domain.Role;
import com.hong610.domain.User;
import com.hong610.service.IRoleService;
import com.hong610.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 角色 Impl
 * Created by Hong on 2016/12/7.
 */
@Service("RoleServiceImpl")
public class RoleServiceImpl implements IRoleService {

    @Autowired
    IRoleDao roleDao;

    @Override
    public List<Role> findRoleByUser(Long userId) {
        return roleDao.findRoleByUser(userId);
    }

    @Override
    public List<Role> findAll() {
        Wrapper<Role> wa = new EntityWrapper<Role>();
        wa.where("is_delete = false");
        wa.and("status = 1");
        return roleDao.selectList(wa);
    }

    @Override
    public Page<Role> findPage(Page<Role> page, String name) {
        page.setRecords(roleDao.findAll(page, name));
        return page;
    }

    @Override
    public Role findById(Long id) {
        Role role = roleDao.selectById(id);
        if(role == null || role.isDelete() || role.getStatus() != Status.NORMAL.getValue()){
            throw new AppException(SystemError.ROLE_UNKNOWN_ERROR);
        }
        return role;
    }

    @Override
    public boolean insert(Role role) {
        role.add();
        boolean flag = roleDao.insert(role);
        if(!flag){
            throw new AppException(SystemError.ROLE_ADD_ERROR);
        }
        return flag;
    }

    @Override
    public boolean update(Role role) {
        role.update();
        boolean flag = roleDao.updateById(role);
        if(!flag){
            throw new AppException(SystemError.ROLE_UPDATE_ERROR);
        }
        return flag;
    }

    @Override
    public boolean remove(Long id) {
        Role role = new Role();
        role.setId(id);
        role.setDelete(Delete.DELETE.getValue());
        boolean flag = roleDao.updateById(role);
        if(!flag){
            throw new AppException(SystemError.ROLE_REMOVE_ERROR);
        }
        return flag;
    }

    @Override
    public boolean removes(String ids) {
        if(StringUtils.isEmpty(ids)){
            throw new AppException(SystemError.ROLE_REMOVES_NOUSER_ERROR);
        }
        boolean flag = roleDao.removes(ids);
        if(!flag){
            throw new AppException(SystemError.ROLE_REMOVES_ERROR);
        }
        return flag;
    }
}
