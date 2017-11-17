package spring.demo.cache;

import java.io.Serializable;

import org.aspectj.lang.JoinPoint;

/**
 * Created by facheng on 17-11-17.
 */
public class CacheParam implements Serializable {
    private static final long serialVersionUID = -7285770910255074974L;

    private JoinPoint joinPoint;

    public static final CacheParam of(JoinPoint joinPoint) {
        CacheParam cacheParam = new CacheParam();
        cacheParam.setJoinPoint(joinPoint);
        return cacheParam;
    }

    public JoinPoint getJoinPoint() {
        return joinPoint;
    }

    public void setJoinPoint(JoinPoint joinPoint) {
        this.joinPoint = joinPoint;
    }
}
