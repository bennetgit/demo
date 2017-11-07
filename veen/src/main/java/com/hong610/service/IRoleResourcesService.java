package com.hong610.service;

/**
 * 角色拥有资源
 * Created by Hong on 2016/12/7.
 */
public interface IRoleResourcesService {

    /**根据角色编辑所属资源**/
    boolean updateByRole(long roleId, String[] ids);
}
