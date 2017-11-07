package spring.demo.security.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import spring.demo.dto.UserDto;
import spring.demo.security.entity.AuthUser;
import spring.demo.security.entity.Authority;
import spring.demo.service.IUserService;

import javax.annotation.Resource;
import javax.transaction.Transactional;

/**
 * Created by wangfacheng on 2017-11-06.
 */
@Service
public class MyUserDetailsService implements UserDetailsService {

    @Resource
    private IUserService userService;

    @Override
    @Transactional
    public AuthUser loadUserByUsername(String username) throws UsernameNotFoundException {

        UserDto userDto = userService.getUserByName(username);
        Authority authority = new Authority();
        authority.setAuthority("ROLE_ADMIN");

        return AuthUser.of(userDto.getId(), userDto.getUserName(), userDto.getPassword(), true, userDto.getMenus(), authority);
    }
}
