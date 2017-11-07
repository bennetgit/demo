package com.hong610.service.impl;

import com.hong610.dao.IUserRoleDao;
import com.hong610.service.IUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户拥有的角色 Impl
 * Created by Hong on 2016/12/7.
 */
@Service("UserRoleServiceImpl")
public class UserRoleServiceImpl implements IUserRoleService {

    @Autowired
    IUserRoleDao userRoleDao;

}
