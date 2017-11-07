package com.hong610.security.service;

import com.hong610.security.entity.MyGrantedAuthority;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.FilterInvocation;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * 自定义权限验证
 * Created by Hong on 2016/12/1.
 */
@Service("AccessDecisionManagerService")
public class AccessDecisionManagerService implements AccessDecisionManager {

    @Override
    public void decide(Authentication authentication, Object o, Collection<ConfigAttribute> collection) throws AccessDeniedException, InsufficientAuthenticationException {
        //访问路径信息
        FilterInvocation filterInvocation = (FilterInvocation) o;
        String url = filterInvocation.getRequestUrl();

        //用户所具有的权限
        Collection<MyGrantedAuthority> authorities = (Collection<MyGrantedAuthority>) authentication.getAuthorities();

        //但执行到这一行，说明没有该资源访问权限
        //throw new AccessDeniedException("权限不足，禁止访问！");
    }

    @Override
    public boolean supports(ConfigAttribute configAttribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
