package spring.demo.service;

/**
 * Created by feng on 17/11/18.
 */
public interface IPrivilegeCacheService {

    boolean needGrant(String privilegeUrl, String requestType);
}
