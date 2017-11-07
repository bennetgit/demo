package com.hong610.service;

import com.hong610.domain.Login;

/**
 * 登录日志
 * Created by Hong on 2016/12/7.
 */
public interface ILoginService {

    /**添加登录日志**/
    boolean insertLogin(Login login);

    /**获取最新一条登录日志**/
    Login findNewestLogin(long userId);

    /**查询登录次数**/
    long findLoginCount(long userId);
}
