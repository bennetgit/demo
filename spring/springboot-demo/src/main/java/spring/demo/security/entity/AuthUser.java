package spring.demo.security.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import spring.demo.dto.MenuDto;

/**
 * Created by wangfacheng on 2017-11-07.
 */
public class AuthUser implements UserDetails {

    private Long id;

    private String username;

    private String password;

    private boolean enable;

    private List<MenuDto> menus = new ArrayList<>();

    private Collection<GrantedAuthority> grantedAuthorityList = new ArrayList<>();

    AuthUser(Long id, String username, String password, boolean enable, List<MenuDto> menus,
            Collection<GrantedAuthority> grantedAuthorityList) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.enable = enable;
        this.grantedAuthorityList = grantedAuthorityList;
        this.menus = menus;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public List<MenuDto> getMenus() {
        return menus;
    }

    public void setMenus(List<MenuDto> menus) {
        this.menus = menus;
    }

    public static final AuthUser of(Long id, String username, String password, boolean enable, List<MenuDto> menus,
            Authority... grantedAuthorityList) {
        return new AuthUser(id, username, password, enable, menus, Arrays.asList(grantedAuthorityList));
    }
}
