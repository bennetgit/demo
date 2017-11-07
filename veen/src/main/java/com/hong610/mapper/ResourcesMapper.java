package com.hong610.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.hong610.domain.Resources;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 访问资源
 * Created by Hong on 2016/12/7.
 */
@Mapper
public interface ResourcesMapper extends BaseMapper<Resources> {

    /**根据用户获取资源**/
    List<Resources> findResourcesByUser(long userId);

    /**根据角色ID查询资源**/
    List<Resources> findResourcesByRoleId(long roleId);
}
