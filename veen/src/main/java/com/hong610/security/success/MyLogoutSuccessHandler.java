package com.hong610.security.success;

import com.hong610.service.ILoginService;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 退出之后的处理
 * Created by Hong on 2016/11/22.
 */
@Service("MyLogoutSuccessHandler")
public class MyLogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler {

    @Resource
    ILoginService loginService;

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        super.onLogoutSuccess(request, response, authentication);
    }
}
