package spring.demo.aspect;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import spring.demo.constant.Constants;

/**
 * Created by facheng on 16.03.17.
 */
@Aspect
@Component
public class WebLogAspect {

    private Logger LOGGER = LoggerFactory.getLogger(getClass());

    // @Pointcut("@annotation(spring.demo.annotation.WebLog) || execution(public
    // * spring.demo.controller..*.*(..))")
    // @within和@target针对类的注解,@annotation是针对方法的注解
    @Pointcut("@within(spring.demo.annotation.WebLog)")
    public void webLog() {
    }

    @Before("webLog()")
    public void deBefore(JoinPoint joinPoint) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        StringBuffer sb = new StringBuffer(StringUtils.EMPTY);

        sb.append("URL : ").append(request.getRequestURI().toString()).append(Constants.BREAK_LINE)
                .append("HTTP_METHOD : ").append(request.getMethod()).append(Constants.BREAK_LINE).append("IP :")
                .append(request.getRemoteAddr()).append("CLASS_METHOD : ").append(Constants.BREAK_LINE)
                .append(joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName())
                .append(Constants.BREAK_LINE).append("ARGS :").append(Arrays.toString(joinPoint.getArgs()));
        LOGGER.info(sb.toString());

    }

    @AfterReturning(returning = "result", pointcut = "webLog()")
    public void doAfterReturning(Object result) {
        LOGGER.info("RESPONSE : {}", result);
    }

}
