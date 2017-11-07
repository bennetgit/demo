package spring.demo.security.entity;

import org.springframework.security.core.GrantedAuthority;

/**
 * Created by wangfacheng on 2017-11-07.
 */
public class Authority implements GrantedAuthority {

    private String authority;

    @Override
    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }
}
