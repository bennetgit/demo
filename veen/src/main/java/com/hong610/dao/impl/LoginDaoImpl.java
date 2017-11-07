package com.hong610.dao.impl;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.hong610.common.enums.Status;
import com.hong610.dao.ILoginDao;
import com.hong610.domain.Login;
import com.hong610.mapper.LoginMapper;
import org.springframework.stereotype.Component;

/**
 * 登录日志 Impl
 * Created by Hong on 2016/12/7.
 */
@Component
public class LoginDaoImpl extends ServiceImpl<LoginMapper, Login> implements ILoginDao {

    @Override
    public Login findNewestLogin(long userId) {
        return baseMapper.findNewestLogin(userId);
    }

    @Override
    public long findLoginCount(final long userId) {
        Wrapper<Login> wa = new Wrapper<Login>() {
            @Override
            public String getSqlSegment() {
                return "where user_id = "+userId+" and status = "+Status.NORMAL.getValue()+" and is_delete = 0";
            }
        };
        return baseMapper.selectCount(wa);
    }
}
