package spring.demo.util;

import spring.demo.constant.Constants;

/**
 * Created by facheng on 17-11-17.
 */
public final class MyCacheUtils {

    private MyCacheUtils() {
    }

    public static final String getRoleCacheKey(Long roleId) {
        return formatKey(Constants.CacheConfig.ROLE_CACHE_KEY, String.valueOf(roleId));
    }

    public static final String getPrivelgeCacheKey(String privilegeUrl) {
        return formatKey(Constants.CacheConfig.PRIVILEGE_CACHE_KEY, privilegeUrl);
    }

    public static final String formatKey(String key, String... part) {
        if (key == null || part == null) {
            return key;
        }

        int partSize = part.length;
        int index = 0;
        String tempKey = key;
        while (tempKey.indexOf(Constants.CacheConfig.CACHE_KEY_SUFFIX) != -1 && index < partSize) {
            tempKey = tempKey.replace(Constants.CacheConfig.CACHE_KEY_SUFFIX, part[index]);
        }

        return tempKey;

    }

    public static String getLowerCaseByClass(Class cacheKey) {
        String className = cacheKey.getSimpleName();
        return className.substring(0, 1).toLowerCase() + className.substring(1);
    }
}
