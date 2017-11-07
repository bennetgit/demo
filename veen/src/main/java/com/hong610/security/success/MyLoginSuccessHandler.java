package com.hong610.security.success;

import com.hong610.common.async.LoginAsync;
import com.hong610.common.exception.error.SystemError;
import com.hong610.common.helper.WebHelper;
import com.hong610.domain.Login;
import com.hong610.security.entity.UserDetail;
import com.hong610.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登录成功
 * Created by Hong on 2016/11/22.
 */
@Service("MyLoginSuccessHandler")
public class MyLoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Autowired
    LoginAsync loginAsync;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        Login login = (Login) request.getSession().getAttribute("Login");

        if(request.getSession().getAttribute("captcha") == null ? false : (Boolean) request.getSession().getAttribute("captcha")){//需要验证验证码
            String captcha = request.getParameter("captcha");//得到到用户输入的验证码
            String kaptchaExpected = (String)request.getSession().getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);//得到框架生成的验证码
            //校验验证码是否正确
            if (StringUtils.isEmpty(captcha) || !captcha.toLowerCase().equals(kaptchaExpected.toLowerCase())){
                request.getSession().setAttribute("login_error", SystemError.USER_CAPTCHA_ERROR.getMessage());
                response.sendRedirect("/account/login.html");
                return;
            }
        }else{
            if(login != null){//比对本次和上一个IP地址时候相同 不同就需要输入验证码
                if(!WebHelper.getIpAddr(request).equals(login.getIpAddress())){
                    request.getSession().setAttribute("captcha", true);//需要输入验证码
                    response.sendRedirect("/account/login.html");
                    return;
                }
            }
        }

        request.getSession().removeAttribute("login_error");
        loginAsync.addLogin(WebHelper.getAgent(request), WebHelper.getIpAddr(request), ((UserDetail)authentication.getPrincipal()).getUserId());
        super.onAuthenticationSuccess(request, response, authentication);
    }
}
