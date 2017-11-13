package spring.demo.util;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by feng on 17/10/29.
 */
public final class StringUtil {
    private StringUtil() {
    }

    public static final String EMPTY = "";

    public static final boolean isNotNull(String str) {
        return str != null;
    }

    public static final boolean isNotBlank(String str) {
        return StringUtils.isNotBlank(str);
    }

    public static final String wildcard(String source) {
        return "%" + source + "%";
    }
}
