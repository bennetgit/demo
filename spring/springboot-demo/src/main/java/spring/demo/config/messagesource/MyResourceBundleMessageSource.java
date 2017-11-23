package spring.demo.config.messagesource;

import java.text.MessageFormat;
import java.util.Locale;

import org.springframework.context.support.AbstractMessageSource;
import org.springframework.stereotype.Component;

/**
 * Created by facheng on 17-11-23.
 */

@Component
public class MyResourceBundleMessageSource extends AbstractMessageSource implements IResourceBundleMessageSource {

    @Override
    protected MessageFormat resolveCode(String code, Locale locale) {
        return null;
    }
}
