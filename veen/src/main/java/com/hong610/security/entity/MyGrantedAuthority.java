package com.hong610.security.entity;

import com.hong610.domain.Resources;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.util.Assert;

/**
 * SpringSecurity 自定义GrantedAuthority
 * Created by Hong on 2016/12/2.
 */
public class MyGrantedAuthority implements GrantedAuthority {
    private static final long serialVersionUID = 320L;

    private String authority;

    private Resources resource;

    public MyGrantedAuthority(Resources resource) {
        this.setAuthority(resource.getAuthority());
        this.setResource(resource);
    }

    public boolean equals(Object obj) {
        if(obj instanceof String) {
            return obj.equals(this.authority);
        } else if(obj instanceof GrantedAuthority) {
            GrantedAuthority attr = (GrantedAuthority)obj;
            return this.authority.equals(attr.getAuthority());
        } else {
            return false;
        }
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return this.authority;
    }

    public Resources getResource() {
        return resource;
    }

    public void setResource(Resources resource) {
        this.resource = resource;
    }

    public int hashCode() {
        return this.getAuthority().hashCode();
    }

    public String toString() {
        return this.getAuthority();
    }
}
