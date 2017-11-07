package spring.demo.security;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
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
        return null;
    }
}
