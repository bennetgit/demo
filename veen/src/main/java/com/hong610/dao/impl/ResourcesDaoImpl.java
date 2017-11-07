package com.hong610.dao.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.hong610.common.enums.Delete;
import com.hong610.common.enums.Status;
import com.hong610.dao.IResourcesDao;
import com.hong610.domain.Resources;
import com.hong610.mapper.ResourcesMapper;
import com.hong610.utils.DateUtils;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 权限资源
 * Created by Hong on 2016/12/7.
 */
@Component
public class ResourcesDaoImpl extends ServiceImpl<ResourcesMapper, Resources> implements IResourcesDao {

    @Override
    public List<Resources> findResourcesByUser(long userId) {
        return baseMapper.findResourcesByUser(userId);
    }

    @Override
    public Resources findByAuthority(String authority) {
        Resources resources = new Resources();
        resources.setAuthority(authority);
        return baseMapper.selectOne(resources);
    }

    @Override
    public boolean removes(String ids) {
        return retBool(baseMapper.updateSql("update user set resources = true, modified_time = '"+ DateUtils.formatNow(DateUtils.YYYYMMDDHHMMSS)+"' where id in ("+ids+")"));
    }

    @Override
    public int getCountByParentId(Long id) {
        Wrapper<Resources> wa = new EntityWrapper<Resources>();
        wa.where("status = " + Status.NORMAL.getValue());
        wa.and("is_delete = " + Delete.UNDELETE.getValue());
        wa.and("parent_id = " + id);
        return baseMapper.selectCount(wa);
    }

    @Override
    public int findByAuthorityExcludeSelf(Long id, String authority) {
        Resources resources = new Resources();
        resources.setAuthority(authority);
        Wrapper<Resources> wa = new EntityWrapper<Resources>();
        wa.where("status = " + Status.NORMAL.getValue());
        wa.and("is_delete = " + Delete.UNDELETE.getValue());
        wa.and("authority = '" + authority + "'");
        wa.and("id != " + id);
        return baseMapper.selectCount(wa);
    }

    @Override
    public List<Resources> findResourcesByRoleId(long roleId) {
        return baseMapper.findResourcesByRoleId(roleId);
    }
}
