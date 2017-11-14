package spring.demo.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import spring.demo.persistence.primary.domain.LoginAudit;
import spring.demo.persistence.primary.jpa.ILoginAuditRepository;
import spring.demo.persistence.primary.jpa.IUserRepository;
import spring.demo.service.ILoginAuditService;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Created by feng on 17/11/14.
 */
@Service
public class LoginAuditServiceImpl implements ILoginAuditService {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginAuditServiceImpl.class);

    @Resource
    private ILoginAuditRepository loginAuditRepository;

    @Resource
    private IUserRepository userRepository;

    @Override
    public void addLoginAudit(Long loginUserId, String userAgent, String ipAddress, Date loginTime) {
        LOGGER.info("start addLoginAudit user {} userAgent {} ipAddress {} loginTime {}", loginUserId, userAgent,
                ipAddress, loginTime);
        loginAuditRepository.save(LoginAudit.of(userRepository.findById(loginUserId), userAgent, ipAddress, loginTime));

    }
}
