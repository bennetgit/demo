package spring.demo.security;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * Created by wangfacheng on 2017-11-07.
 */
public class AuthUser implements UserDetails {

    private Long id;

    private String username;

    private String password;

    private boolean enable;

    private Collection<GrantedAuthority> grantedAuthorityList = new ArrayList<>();

    AuthUser(Long id, String username, String password, boolean enable,
            Collection<GrantedAuthority> grantedAuthorityList) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.enable = enable;
        this.grantedAuthorityList = grantedAuthorityList;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return grantedAuthorityList;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enable;
    }

    public static final AuthUser of(Long id, String username, String password, boolean enable,
            Collection<GrantedAuthority> grantedAuthorityList) {
        return new AuthUser(id, username, password, enable, grantedAuthorityList);
    }
}
