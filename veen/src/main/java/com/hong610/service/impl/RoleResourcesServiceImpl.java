package com.hong610.service.impl;

import com.hong610.common.enums.Delete;
import com.hong610.common.enums.Status;
import com.hong610.common.exception.AppException;
import com.hong610.common.exception.error.SystemError;
import com.hong610.dao.IRoleResourcesDao;
import com.hong610.domain.RoleResources;
import com.hong610.service.IRoleResourcesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 角色拥有资源 Impl
 * Created by Hong on 2016/12/7.
 */
@Service("RoleResourcesServiceImpl")
public class RoleResourcesServiceImpl implements IRoleResourcesService {

    @Autowired
    IRoleResourcesDao roleResourcesDao;

    @Override
    public boolean updateByRole(long roleId, String[] ids) {
        if (ids == null || ids.length == 0) {
            throw new AppException(SystemError.ROLERESOURCES_EDITBYROLE_NULL_ERROR);
        }
        boolean flag = roleResourcesDao.removesByRole(roleId);
        for (String id : ids) {
            long resourceId = Long.valueOf(id);
            RoleResources roleResources = new RoleResources();
            roleResources.setResourceId(resourceId);
            roleResources.setDelete(Delete.UNDELETE.getValue());
            roleResources.setStatus(Status.NORMAL.getValue());
            roleResources.setRoleId(roleId);
            roleResources.add();
            flag = roleResourcesDao.insert(roleResources);
            if(!flag)
                throw new AppException(SystemError.ROLERESOURCES_EDITBYROLE_ERROR);
        }
        return flag;
    }
}
