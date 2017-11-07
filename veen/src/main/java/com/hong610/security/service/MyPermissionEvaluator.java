package com.hong610.security.service;

import com.hong610.security.entity.MyGrantedAuthority;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Collection;

/**
 * 使用hasPermission自定义验证
 * Created by Hong on 2016/12/17.
 */
@Service("MyPermissionEvaluator")
public class MyPermissionEvaluator implements PermissionEvaluator {

    @Override
    public boolean hasPermission(Authentication authentication, Object o, Object o1) {
        if ("skip".equals(o)) {
            return true;
        }
        if ("permission".equals(o)) {
            return this.hasPermission(authentication, o1);
        }
        return false;
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable serializable, String s, Object o) {
        return true;
    }

    /**
     * 简单的字符串比较，相同则认为有权限
     */
    private boolean hasPermission(Authentication authentication, Object permission) {
        Collection<MyGrantedAuthority> authorities = (Collection<MyGrantedAuthority>) authentication.getAuthorities();
        for (MyGrantedAuthority authority : authorities) {
            if (authority.equals(permission)) {
                return true;
            }
        }
        return false;
    }
}
