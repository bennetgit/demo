package com.hong610.security.service;

import com.alibaba.fastjson.JSON;
import com.hong610.common.exception.error.SystemError;
import com.hong610.common.helper.HttpHelper;
import com.hong610.common.helper.PasswordHelper;
import com.hong610.security.entity.UserDetail;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * 自定义验证
 * Created by Hong on 2016/12/7.
 */
@Service("MyAuthenticationProvider")
public class MyAuthenticationProvider implements AuthenticationProvider {
	
	@Resource(name = "UserDetailService")
	UserDetailService userDetailService;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String username = authentication.getName();
        String password = (String) authentication.getCredentials();
        UserDetail user = (UserDetail) userDetailService.loadUserByUsername(username);
		HttpSession session = HttpHelper.getHttpServletRequest().getSession();
		if(!user.getPassword().equals(PasswordHelper.password(username.concat(password)))){
			session.setAttribute("login_error", SystemError.USER_PASSWORD_ERROR.getMessage());
        	throw new BadCredentialsException(JSON.toJSONString(SystemError.USER_PASSWORD_ERROR));
        }
		return new UsernamePasswordAuthenticationToken(user, password, user.getAuthorities());
	}

	@Override
	public boolean supports(Class<?> paramClass) {
		return true;
	}

}
