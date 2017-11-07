package com.hong610.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.hong610.domain.UserRole;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户拥有的角色
 * Created by Hong on 2016/12/7.
 */
@Mapper
public interface UserRoleMapper extends BaseMapper<UserRole> {

}
