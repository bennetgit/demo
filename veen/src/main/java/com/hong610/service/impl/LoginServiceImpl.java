package com.hong610.service.impl;

import com.hong610.dao.ILoginDao;
import com.hong610.domain.Login;
import com.hong610.service.ILoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 登录日志 Impl
 * Created by Hong on 2016/12/7.
 */
@Service("LoginServiceImpl")
public class LoginServiceImpl implements ILoginService {

    @Autowired
    ILoginDao loginDao;

    @Override
    public boolean insertLogin(Login login) {
        return loginDao.insert(login);
    }

    @Override
    public Login findNewestLogin(long userId) {
        return loginDao.findNewestLogin(userId);
    }

    @Override
    public long findLoginCount(long userId) {
        return loginDao.findLoginCount(userId);
    }
}
