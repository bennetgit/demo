package spring.demo.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import spring.demo.security.entity.AuthUser;

/**
 * Created by facheng on 17-11-15.
 */
public abstract class BaseController {

    protected Long getCurrentUserId() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            return null;
        }

        Object principal = authentication.getPrincipal();
        if (principal == null || (principal instanceof AuthUser)) {
            return null;
        }
        return ((AuthUser) principal).getId();
    }
}
