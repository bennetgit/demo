package spring.demo.security.service;

import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.stereotype.Service;

/**
 * Created by wangfacheng on 2017-11-08.
 */

@Service
public class MySessionRegistry extends SessionRegistryImpl {
    @Override
    public void refreshLastRequest(String sessionId) {
        super.refreshLastRequest(sessionId);
    }

    @Override
    public void registerNewSession(String sessionId, Object principal) {
        super.registerNewSession(sessionId, principal);
    }

    @Override
    public void removeSessionInformation(String sessionId) {
        super.removeSessionInformation(sessionId);
    }
}
