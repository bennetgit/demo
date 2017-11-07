package com.hong610.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.hong610.domain.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 角色
 * Created by Hong on 2016/12/7.
 */
@Mapper
public interface RoleMapper extends BaseMapper<Role> {

    /**根据用户ID查询角色**/
    List<Role> findRoleByUser(Long userId);

    /**查询全部角色**/
    List<Role> findAll(Page<Role> page, @Param("name") String name);
}
