package spring.demo.security.service;

import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.util.CollectionUtils;

import spring.demo.security.entity.AuthUser;
import spring.demo.service.IPrivilegeCacheService;
import spring.demo.service.IRoleCacheService;
import spring.demo.util.RequestUtils;

/**
 * Created by wangfacheng on 2017-11-07.
 */
public class MyAccessDecisionManager implements AccessDecisionManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(MyAccessDecisionManager.class);

    @Resource
    private IRoleCacheService roleCacheService;

    @Resource
    private IPrivilegeCacheService privilegeCacheService;

    @Override
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes)
            throws AccessDeniedException, InsufficientAuthenticationException {

        LOGGER.info("request address {}", RequestUtils.getRequestAddress());

        if (authentication == null || !(authentication.getPrincipal() instanceof AuthUser)) {
            throw new AccessDeniedException("can not access, " + object);
        }

        AuthUser authUser = (AuthUser) authentication.getPrincipal();
        if (!hasPermit(RequestUtils.getRequestAddress(), authUser)) {

            throw new AccessDeniedException("can not access, " + object);
        }

    }

    private boolean hasPermit(String targetUrl, AuthUser authUser) {

        if (authUser == null || authUser.isEnabled()) {
            return false;
        }

        if (authUser.isSuperUser()) {
            return true;
        }

        if (!privilegeCacheService.needGrant(targetUrl)) {
            return true;
        }

        List<Long> roleIds = authUser.getRoleIds();

        if (CollectionUtils.isEmpty(roleIds)) {
            return false;
        }

        return roleIds.stream().anyMatch(roleId -> roleCacheService.hasPermit(roleId, targetUrl));
    }

    @Override
    public boolean supports(ConfigAttribute attribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}
