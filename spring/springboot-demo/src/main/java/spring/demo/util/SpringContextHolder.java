package spring.demo.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * Created by facheng on 17-11-17.
 */
public class SpringContextHolder implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public static final ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public static final Object getBean(String beanName) {
        return applicationContext.getBean(beanName);
    }

    public static final <T> T getBean(String beanName, Class<T> clazz) {
        return applicationContext.getBean(clazz, beanName);
    }
}
