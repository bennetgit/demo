package spring.demo.service;

import java.util.Date;

/**
 * Created by feng on 17/11/14.
 */
public interface ILoginAuditService {

    void addLoginAudit(Long loginUserId, String userAgent, String ipAddress, Date loginTime);
}
