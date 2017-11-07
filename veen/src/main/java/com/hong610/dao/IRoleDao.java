package com.hong610.dao;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.hong610.domain.Role;

import java.util.List;

/**
 * 角色
 * Created by Hong on 2016/12/7.
 */
public interface IRoleDao extends IService<Role> {

    /**根据用户ID查询角色**/
    List<Role> findRoleByUser(Long userId);

    /**批量删除角色**/
    boolean removes(String ids);

    /**查询全部角色**/
    List<Role> findAll(Page<Role> page, String name);
}
