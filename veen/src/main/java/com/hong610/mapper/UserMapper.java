package com.hong610.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.hong610.domain.User;
import com.hong610.domain.vo.UserAdmin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户
 * Created by Hong on 2016/12/7.
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    /**查询管理员集合**/
    List<UserAdmin> findAdmin(Page<UserAdmin> page, @Param("admin") int admin, @Param("nick") String nick);
}
