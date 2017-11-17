package spring.demo.exception;

/**
 * Created by wangfacheng on 2017-11-13.
 */
public class UnSupportCacheException extends RuntimeException {

    public UnSupportCacheException() {
        super();
    }

    public UnSupportCacheException(String message) {
        super(message);
    }

    public UnSupportCacheException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnSupportCacheException(Throwable cause) {
        super(cause);
    }

    protected UnSupportCacheException(String message, Throwable cause, boolean enableSuppression,
                                      boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
