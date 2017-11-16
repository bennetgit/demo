package spring.demo.aspect;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

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
        LOGGER.info("URL : {}", request.getRequestURI().toString());
        LOGGER.info("HTTP_METHOD : {}", request.getMethod());
        LOGGER.info("IP : {}", request.getRemoteAddr());
        LOGGER.info("CLASS_METHOD : {}",
                joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        LOGGER.info("ARGS : {}", Arrays.toString(joinPoint.getArgs()));

    }

    @AfterReturning(returning = "result", pointcut = "webLog()")
    public void doAfterReturning(Object result) {
        LOGGER.info("RESPONSE : {}", result);
    }

}
