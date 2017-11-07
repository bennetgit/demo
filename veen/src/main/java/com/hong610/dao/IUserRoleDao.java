package com.hong610.dao;

import com.baomidou.mybatisplus.service.IService;
import com.hong610.domain.UserRole;
/**
 * 用户拥有的角色
 * Created by Hong on 2016/12/7.
 */
public interface IUserRoleDao extends IService<UserRole> {

    /**根据用户ID逻辑删除用户角色**/
    boolean removeByUserId(long userId);

    /**撤销删除**/
    boolean reDeleteByUserId(long userId, long roleId);
}
