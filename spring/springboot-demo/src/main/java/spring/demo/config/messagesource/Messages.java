package spring.demo.config.messagesource;

import java.util.Locale;

import org.springframework.context.i18n.LocaleContextHolder;

/**
 * Created by feng on 17/11/21.
 */
public final class Messages {

    private static MyMessageSourceAccessor messageSourceAccessor = new MyMessageSourceAccessor();

    private Messages() {
    }

    public static final String get(String key, String... part) {
        return messageSourceAccessor.get(key, part);
    }

    public static final void setLocale(Locale locale) {
        LocaleContextHolder.setLocale(locale);
    }

    public static final Locale getLocale() {
        return LocaleContextHolder.getLocale();
    }
}
