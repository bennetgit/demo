package spring.demo.util;

/**
 * Created by facheng on 17-11-17.
 */
public final class MyCacheUtils {

    private MyCacheUtils() {
    }

    public static String getBeanNameByClass(Class cacheEngine) {
        String className = cacheEngine.getSimpleName();
        return className.substring(0, 1).toLowerCase() + className.substring(1);
    }
}
