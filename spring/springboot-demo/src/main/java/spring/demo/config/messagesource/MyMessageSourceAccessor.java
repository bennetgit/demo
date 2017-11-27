package spring.demo.config.messagesource;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.web.context.support.WebApplicationObjectSupport;

import spring.demo.util.SpringContextHolder;

/**
 * Created by feng on 17/11/21.
 */

@Configurable(preConstruction = true)
public class MyMessageSourceAccessor extends WebApplicationObjectSupport {

    private MessageSourceAccessor messageSourceAccessor;

    public MyMessageSourceAccessor() {

        try {
            getMessageSourceAccessor();
        } catch (IllegalStateException e) {
            if (SpringContextHolder.getApplicationContext() != null) {
                setApplicationContext(SpringContextHolder.getApplicationContext());
            }
        }

        this.messageSourceAccessor = getMessageSourceAccessor();
    }

    public String get(String key, String... part) {
        try {
            return messageSourceAccessor.getMessage(key, part);
        } catch (Exception e) {
            return key;
        }
    }

}
