package spring.demo.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import spring.demo.constant.Constants;
import spring.demo.persistence.primary.domain.Privilege;
import spring.demo.persistence.primary.jpa.IPrivilegeRepository;
import spring.demo.service.IPrivilegeCacheService;

/**
 * Created by facheng on 17-11-17.
 */

@Service("privilegeCacheServiceImpl")
public class PrivilegeCacheServiceImpl extends AbstractCacheServiceImpl<String, Privilege>
        implements IPrivilegeCacheService {

    @Resource
    private IPrivilegeRepository privilegeRepository;

    @Override
    protected String getCacheName() {
        return Constants.CacheConfig.CACHE_NAME;
    }

    @Transactional
    public void initCache() {
        LOGGER.info("privilege cache init start...");
        long start = System.currentTimeMillis();
        List<Privilege> privileges = privilegeRepository.findAll();

        if (CollectionUtils.isEmpty(privileges)) {
            LOGGER.info("privilege cache init end, privilege is empty");
            return;
        }

        privileges.stream().forEach(privilege -> add(privilege.getUrl(), privilege));
        LOGGER.info("privilege cache init end, cost {}ms ", System.currentTimeMillis() - start);
    }

    @Override
    public boolean needGrant(String privilegeUrl) {
        return get(privilegeUrl) != null;
    }
}
