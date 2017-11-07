package com.hong610.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.hong610.common.enums.Delete;
import com.hong610.common.enums.Status;
import com.hong610.common.exception.AppException;
import com.hong610.common.exception.error.SystemError;
import com.hong610.dao.IResourcesDao;
import com.hong610.domain.Resources;
import com.hong610.service.IResourcesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 权限资源 Impl
 * Created by Hong on 2016/12/7.
 */
@Service("ResourcesServiceImpl")
public class ResourcesServiceImpl implements IResourcesService {

    @Autowired
    IResourcesDao resourcesDao;

    @Override
    public List<Resources> findResourcesByUser(long userId) {
        return resourcesDao.findResourcesByUser(userId);
    }

    @Override
    public boolean insert(Resources resources) {
        if (resourcesDao.findByAuthority(resources.getAuthority()) != null) {
            throw new AppException(SystemError.RESOURCES_REPEAT_ERROR);
        }
        resources.add();
        boolean flag = resourcesDao.insert(resources);
        if (!flag) {
            throw new AppException(SystemError.RESOURCES_ADD_ERROR);
        }
        return flag;
    }

    @Override
    public boolean update(Resources resources) {
        if (resourcesDao.findByAuthorityExcludeSelf(resources.getId(), resources.getAuthority()) > 0) {
            throw new AppException(SystemError.RESOURCES_ANTHORITY_REPEAT_ERROR);
        }
        resources.update();
        boolean flag = resourcesDao.updateById(resources);
        if (!flag) {
            throw new AppException(SystemError.RESOURCES_EDIT_ERROR);
        }
        return flag;
    }

    @Override
    public boolean remove(Long id) {
        if (resourcesDao.getCountByParentId(id) > 0) {
            throw new AppException(SystemError.RESOURCES_REMOVE_DOWN_LIST_ERROR);
        }
        Resources resources = new Resources();
        resources.setId(id);
        resources.setDelete(Delete.DELETE.getValue());
        resources.update();
        boolean flag = resourcesDao.updateById(resources);
        if (!flag) {
            throw new AppException(SystemError.RESOURCES_REMOVE_ERROR);
        }
        return flag;
    }

    @Override
    public Resources findById(Long id) {
        Resources resource = resourcesDao.selectById(id);
        if (resource == null || resource.isDelete() || resource.getStatus() != Status.NORMAL.getValue()) {
            throw new AppException(SystemError.RESOURCES_UNKNOWN_ERROR);
        }
        return resource;
    }

    @Override
    public List<Resources> findByType(int type) {
        Wrapper<Resources> wa = new EntityWrapper<Resources>();
        wa.where("type = " + type);
        wa.and("status = " + Status.NORMAL.getValue());
        wa.and("is_delete = " + Delete.UNDELETE.getValue());
        wa.orderBy("sort", true);
        return resourcesDao.selectList(wa);
    }

    @Override
    public List<Resources> findByTypeAndParentId(int type, long parentId) {
        Wrapper<Resources> wa = new EntityWrapper<Resources>();
        wa.where("type = " + type);
        wa.and("status = " + Status.NORMAL.getValue());
        wa.and("is_delete = " + Delete.UNDELETE.getValue());
        wa.and("parent_id = " + parentId);
        wa.orderBy("sort", true);
        return resourcesDao.selectList(wa);
    }

    @Override
    public List<Resources> findResourcesByRoleId(long roleId) {
        return resourcesDao.findResourcesByRoleId(roleId);
    }
}
