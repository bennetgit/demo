package spring.demo.security.success;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import spring.demo.config.async.LogAsync;

/**
 * Created by wangfacheng on 2017-11-14.
 */
public class MyLoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Resource
    private LogAsync logAsync;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
        logAsync.addLoginLog();

        setAlwaysUseDefaultTargetUrl(true);
        setDefaultTargetUrl("/index");
        super.onAuthenticationSuccess(request, response, authentication);
    }
}
