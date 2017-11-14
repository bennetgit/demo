package spring.demo.config.async;

import java.util.Date;
import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;
import spring.demo.service.ILoginAuditService;

import javax.annotation.Resource;

/**
 * Created by wangfacheng on 2017-11-14.
 */

@Component
public class LogAsync {

    private static final Logger LOGGER = LoggerFactory.getLogger(LogAsync.class);

    @Resource
    private ILoginAuditService loginAuditService;

    @Async("threadPoolTaskExecutor")
    public Future<String> addLoginLog(Long loginUserId, String userAgent, String ipAddress, Date loginTime) {
        LOGGER.info("start async to execute task login user id {}", loginUserId);
        loginAuditService.addLoginAudit(loginUserId, userAgent, ipAddress, loginTime);
        return new AsyncResult<>("success to add login log info");
    }
}
