package spring.demo.config.messagesource;

import org.springframework.context.support.ApplicationObjectSupport;
import org.springframework.context.support.MessageSourceAccessor;

/**
 * Created by feng on 17/11/21.
 */

public class MyMessageSourceAccessor extends ApplicationObjectSupport {

    private MessageSourceAccessor messageSourceAccessor;

    public MyMessageSourceAccessor() {
        this.messageSourceAccessor = getMessageSourceAccessor();
    }
}
