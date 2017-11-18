package spring.demo.util;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * Created by feng on 17/11/18.
 */
public final class RequestUtils {

    private RequestUtils() {
    }

    public static final String getRequestAddress() {

        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getServletPath();
    }
}
