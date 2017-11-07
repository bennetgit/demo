package com.hong610.service;

import com.hong610.domain.Resources;

import java.util.List;

/**
 * 权限资源
 * Created by Hong on 2016/12/7.
 */
public interface IResourcesService {

    /**根据用户获取资源**/
    List<Resources> findResourcesByUser(long userId);

    /**添加资源**/
    boolean insert(Resources resources);

    /**修改资源**/
    boolean update(Resources resources);

    /**删除资源**/
    boolean remove(Long id);

    /**根据ID查询**/
    Resources findById(Long id);

    /**根据资源类型查询资源**/
    List<Resources> findByType(int type);

    /**根据父ID和类型查询资源**/
    List<Resources> findByTypeAndParentId(int type, long parentId);

    /**根据角色ID查询资源**/
    List<Resources> findResourcesByRoleId(long roleId);
}
