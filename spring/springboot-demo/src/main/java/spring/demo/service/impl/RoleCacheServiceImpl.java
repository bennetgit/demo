package spring.demo.service.impl;

import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import spring.demo.constant.Constants;
import spring.demo.persistence.primary.domain.Role;
import spring.demo.persistence.primary.jpa.IPrivilegeRepository;
import spring.demo.persistence.primary.jpa.IRoleRepository;
import spring.demo.util.MyCacheUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by facheng on 17-11-17.
 */

@Service("roleCacheServiceImpl")
public class RoleCacheServiceImpl extends AbstractCacheServiceImpl {

    @Resource
    private IRoleRepository roleRepository;

    @Resource
    private IPrivilegeRepository privilegeRepository;

    @Override
    protected String getCacheName() {
        return Constants.CacheConfig.CACHE_NAME;
    }

    @Transactional
    public void initCache() {
        List<Role> roles = roleRepository.findAll();

        if (CollectionUtils.isEmpty(roles)) {
            LOGGER.info("role cache init end, privilege is empty");
            return;
        }

        roles.stream().forEach(role -> addToCache(role));
    }

    private void addToCache(Role role) {
        privilegeRepository.findPrivilegeWithRoleId(role.getId()).stream()
                .forEach(privilege -> add(MyCacheUtils.formatKey(Constants.CacheConfig.ROLE_CACHE_KEY,
                        String.valueOf(role.getId()), privilege.getUrl()), role));
    }
}
