package com.hong610.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.hong610.domain.Login;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户登录
 * Created by Hong on 2016/12/7.
 */
@Mapper
public interface LoginMapper extends BaseMapper<Login> {

    /**获取最新一条登录日志**/
    Login findNewestLogin(long userId);
}
