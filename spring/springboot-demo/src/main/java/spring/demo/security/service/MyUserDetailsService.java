package spring.demo.security.service;

import javax.annotation.Resource;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import spring.demo.dto.UserDto;
import spring.demo.security.entity.AuthUser;
import spring.demo.security.entity.Authority;
import spring.demo.service.IUserService;

/**
 * Created by wangfacheng on 2017-11-06.
 */
public class MyUserDetailsService implements UserDetailsService {

    @Resource
    private IUserService userService;

    @Override
    public AuthUser loadUserByUsername(String username) throws UsernameNotFoundException {

        UserDto userDto = userService.getUserByName(username);

        if (userDto == null) {
            return null;
        }

        Authority authority = new Authority();
        authority.setAuthority("ROLE_ADMIN");

        return AuthUser.of(userDto.getId(), userDto.getUsername(), userDto.getPassword(), true, userDto.getMenus(),
                authority);
    }
}
