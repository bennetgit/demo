package com.hong610.dao.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.hong610.dao.IRoleResourcesDao;
import com.hong610.domain.RoleResources;
import com.hong610.mapper.RoleResourcesMapper;
import com.hong610.utils.DateUtils;
import org.springframework.stereotype.Component;

/**
 * 角色拥有资源 Impl
 * Created by Hong on 2016/12/7.
 */
@Component
public class RoleResourcesDaoImpl extends ServiceImpl<RoleResourcesMapper, RoleResources> implements IRoleResourcesDao {

    @Override
    public boolean removesByRole(long roleId) {
        return retBool(baseMapper.updateSql("update role_resources set is_delete = true, modified_time = '"+ DateUtils.formatNow(DateUtils.YYYYMMDDHHMMSS)+"' where role_id = "+roleId));
    }
}
