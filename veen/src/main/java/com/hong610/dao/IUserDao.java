package com.hong610.dao;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.hong610.domain.User;
import com.hong610.domain.vo.UserAdmin;

import java.util.List;

/**
 * 用户
 * Created by Hong on 2016/12/7.
 */
public interface IUserDao extends IService<User> {

    /**根据登录名查询账户**/
	User findByUserName(String userName);

    /**查询管理员集合**/
    List<UserAdmin> findAdmin(Page<UserAdmin> page, String nick);

    /**批量删除用户**/
    boolean removes(String ids);
}
