package spring.demo.service;

/**
 * Created by feng on 17/11/18.
 */
public interface IRoleCacheService {

    boolean hasPermit(Long roleId, String privilegeUrl);
}
