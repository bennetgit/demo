package com.hong610.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.hong610.domain.Role;

import java.util.List;

/**
 * 角色
 * Created by Hong on 2016/12/7.
 */
public interface IRoleService {

    /**根据用户ID查询角色**/
    List<Role> findRoleByUser(Long userId);

    /**获取所有角色**/
    List<Role> findAll();

    /**角色分页**/
    Page<Role> findPage(Page<Role> page, String name);

    /**根据id查询角色**/
    Role findById(Long id);

    /**添加角色**/
    boolean insert(Role role);

    /**修改角色**/
    boolean update(Role role);

    /**根据ID删除**/
    boolean remove(Long id);

    /**批量删除**/
    boolean removes(String ids);
}
