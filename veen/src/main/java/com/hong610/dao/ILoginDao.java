package com.hong610.dao;

import com.baomidou.mybatisplus.service.IService;
import com.hong610.domain.Login;

/**
 * 登录日志
 * Created by Hong on 2016/12/07.
 */
public interface ILoginDao extends IService<Login> {

    /**获取最新一条登录日志**/
    Login findNewestLogin(long userId);

    /**查询登录次数**/
    long findLoginCount(long userId);
}
