package com.hong610.dao;

import com.baomidou.mybatisplus.service.IService;
import com.hong610.domain.RoleResources;

/**
 * 角色拥有资源
 * Created by Hong on 2016/12/7.
 */
public interface IRoleResourcesDao extends IService<RoleResources> {

    /**根据角色ID删除**/
    boolean removesByRole(long roleId);
}
