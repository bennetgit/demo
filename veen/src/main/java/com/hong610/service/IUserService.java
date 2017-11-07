package com.hong610.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.hong610.domain.User;
import com.hong610.domain.vo.UserAdmin;

/**
 * 用户
 * Created by Hong on 2016/12/7.
 */
public interface IUserService {

    /**根据用户名查询用户**/
	User findByName(String userName);

    /**查询管理员分页**/
    Page<UserAdmin> findAdminPage(Page<UserAdmin> page, String nick);

    /**添加管理员**/
    boolean insertAdmin(String nick, String userName, String password);

    /**修改状态**/
    boolean updateStatus(Long id, Integer status);

    /**删除用户**/
    boolean remove(Long id);

    /**批量删除用户**/
    boolean removes(String ids);

    /**根据用户ID获取当前用户**/
    User findById(Long id);

    /**修改用户**/
    boolean update(User user, String[] roleIds);

}
