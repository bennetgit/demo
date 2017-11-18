package spring.demo.service.impl;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import spring.demo.constant.Constants;
import spring.demo.persistence.primary.domain.Privilege;
import spring.demo.persistence.primary.domain.Role;
import spring.demo.persistence.primary.jpa.IPrivilegeRepository;
import spring.demo.persistence.primary.jpa.IRoleRepository;
import spring.demo.service.IRoleCacheService;
import spring.demo.util.MyCacheUtils;

/**
 * Created by facheng on 17-11-17.
 */

@Service("roleCacheServiceImpl")
public class RoleCacheServiceImpl extends AbstractCacheServiceImpl<String, Set<String>> implements IRoleCacheService {

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

        Set<String> privilegeUrls = privilegeRepository.findPrivilegeWithRoleId(role.getId()).stream()
                .map(Privilege::getUrl).collect(Collectors.toSet());
        add(MyCacheUtils.getRoleCacheKey(role.getId()), privilegeUrls);
    }

    @Override
    public boolean hasPermit(Long roleId, String privilegeUrl) {

        return get(MyCacheUtils.getRoleCacheKey(roleId)) == null ? false
                : get(MyCacheUtils.getRoleCacheKey(roleId)).contains(privilegeUrl);

    }
}
