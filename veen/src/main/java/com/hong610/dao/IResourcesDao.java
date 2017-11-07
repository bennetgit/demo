package com.hong610.dao;

import com.baomidou.mybatisplus.service.IService;
import com.hong610.domain.Resources;

import java.util.List;

/**
 * 权限资源
 * Created by Hong on 2016/12/07
 */
public interface IResourcesDao extends IService<Resources> {

    /**根据用户获取资源**/
    List<Resources> findResourcesByUser(long userId);

    /**根据权限名查询资源**/
    Resources findByAuthority(String authority);

    /**批量删除资源**/
    boolean removes(String ids);

    /**根据ParentId查询下面是否存在资源**/
    int getCountByParentId(Long id);

    /**排除自己查询其它权限名是不是很自己相同了**/
    int findByAuthorityExcludeSelf(Long id, String authority);

    /**根据角色ID查询资源**/
    List<Resources> findResourcesByRoleId(long roleId);
}
