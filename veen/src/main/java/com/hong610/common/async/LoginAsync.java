package com.hong610.common.async;

import com.hong610.domain.Login;
import com.hong610.common.enums.Delete;
import com.hong610.common.enums.Status;
import com.hong610.common.enums.types.LoginType;
import com.hong610.security.entity.UserDetail;
import com.hong610.service.ILoginService;
import com.hong610.common.helper.WebHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.concurrent.Future;

/**
 * 记录登录日志的异步方法
 * Created by Hong on 2016/11/28.
 */
@Component
public class LoginAsync {

    @Resource(name = "LoginServiceImpl")
    ILoginService loginService;

    /**
     * 写入登录日志
     */
    @Async("threadPoolTaskExecutor")
    public Future<String> addLogin(String agent, String ipAddr, Long userId){
        try{
            Login login = new Login();
            login.setType(LoginType.WEB_LOGIN.getValue());
            login.setDelete(Delete.UNDELETE.getValue());
            login.setStatus(Status.NORMAL.getValue());
            login.setUserAgent(agent);
            login.setIpAddress(ipAddr);
            login.setUserId(userId);
            login.add();
            loginService.insertLogin(login);
        }catch (Exception e){
            e.printStackTrace();
            return new AsyncResult<String>("添加登录日志失败！");
        }
        return new AsyncResult<String>("添加登录日志完成！");
    }
}
