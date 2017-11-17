package spring.demo.constant;

public class Constants {

    public static final String FULL_DATE_PATTERN = "yyyy-MM-dd HH:mm:ss";

    public static final int DEFAULT_THREAD_POOL_SIZE = 5;
    public static final int DEFAULT_MAXIMUM_POOL_SIZE = 10;
    public static final int DEFAULT_KEEP_ALIVE_TIME = 100;

    public static final String DEFAULT_CHAR_SET = "UTF-8";

    public static final String DEFAULT_EXECUTOR_NAME_PATTERN = "Performance_Test_%d";

    public static final String DEFAULT_RABBIT_QUEUE_NAME = "spring.demo.rabbitmq";

    public static final String BREAK_LINE = "\n";

    public static class ResponseMsg {
        public static final String SUCCESS = "success";

        public static final String FAIL = "fail";
    }

    public static class CacheConfig {

        public static final String CACHE_NAME_PRIVILEGE = "CACHE_NAME_PRIVILEGE";

        public static final String CACHE_KEY_SUFFIX = "{id}";

        public static final String CACHE_KEY_PREFIX = "privilege:" + CACHE_KEY_SUFFIX;
    }
}
