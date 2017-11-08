package spring.demo.security.service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import spring.demo.security.entity.AuthUser;
import spring.demo.util.helper.HttpHelper;
import spring.demo.util.helper.PasswordHelper;

/**
 * Created by wangfacheng on 2017-11-08.
 */
@Service
public class MyAuthenticationProvider implements AuthenticationProvider {

    @Resource
    private MyUserDetailsService myUserDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String username = authentication.getName();
        String password = (String) authentication.getCredentials();
        AuthUser authUser = myUserDetailsService.loadUserByUsername(username);
        HttpSession session = HttpHelper.getHttpServletRequest().getSession();

        if (authUser == null || !StringUtils.equals(authUser.getPassword(), PasswordHelper.password(password))) {
            session.setAttribute("login_error", "error");
            throw new BadCredentialsException("user authenticate error");
        }
        return new UsernamePasswordAuthenticationToken(authUser, password, authUser.getAuthorities());

    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}
