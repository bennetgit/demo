package spring.demo.security.success;

import java.io.IOException;
import java.time.Instant;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import spring.demo.config.async.LogAsync;
import spring.demo.security.entity.AuthUser;
import spring.demo.util.helper.WebHelper;

/**
 * Created by wangfacheng on 2017-11-14.
 */
public class MyLoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Resource
    private LogAsync logAsync;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {

        if (authentication == null || authentication.getPrincipal() == null
                || !(authentication.getPrincipal() instanceof AuthUser)) {
            return;
        }

        AuthUser authUser = (AuthUser) authentication.getPrincipal();

        logAsync.addLoginLog(authUser.getId(), WebHelper.getAgent(request), WebHelper.getIpAddress(request),
                Date.from(Instant.now()));

        setAlwaysUseDefaultTargetUrl(true);
        setDefaultTargetUrl("/index");
        super.onAuthenticationSuccess(request, response, authentication);
    }
}
