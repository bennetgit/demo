package spring.demo.security.service;

import java.util.Collection;
import java.util.Iterator;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

/**
 * Created by wangfacheng on 2017-11-07.
 */
public class MyAccessDecisionManager implements AccessDecisionManager {

    @Override
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes)
            throws AccessDeniedException, InsufficientAuthenticationException {

        if (configAttributes == null) {
            return;
        }

        Iterator<ConfigAttribute> ite = configAttributes.iterator();
        String needRole;
        while (ite.hasNext()) {
            ConfigAttribute ca = ite.next();
            needRole = ca.getAttribute();

            // if need role is null, we think the resource do not need to
            // authentication
            if (needRole == null) {
                return;
            }

            for (GrantedAuthority ga : authentication.getAuthorities()) {
                if (StringUtils.equalsIgnoreCase(needRole, ga.getAuthority())) {
                    return;
                }
            }
        }

        throw new AccessDeniedException("can not access, " + object);

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
