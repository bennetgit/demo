package spring.demo.security.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import spring.demo.dto.MenuDto;
import spring.demo.dto.UserDto;
import spring.demo.security.entity.AuthUser;
import spring.demo.security.entity.Authority;
import spring.demo.service.IMenuService;
import spring.demo.service.IUserService;

/**
 * Created by wangfacheng on 2017-11-06.
 */
public class MyUserDetailsService implements UserDetailsService {

    @Resource
    private IUserService userService;

    @Resource
    private IMenuService menuService;

    @Override
    public AuthUser loadUserByUsername(String username) throws UsernameNotFoundException {

        UserDto userDto = userService.getUserByName(username);

        if (userDto == null) {
            return null;
        }

        Authority authority = new Authority();
        List<MenuDto> menus;
        boolean isSuperUser = userDto.getAdmin() != null && userDto.getAdmin();
        if (isSuperUser) {
            menus = menuService.findAll();
        } else {
            menus = userDto.getMenus();
        }

        return AuthUser.of(userDto.getId(), userDto.getUsername(), userDto.getPassword(), true, menus, authority)
                .withRoleIds(userDto.getRoleIds()).withSuperUser(isSuperUser);
    }
}
