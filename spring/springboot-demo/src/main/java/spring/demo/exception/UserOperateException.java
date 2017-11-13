package spring.demo.exception;

/**
 * Created by wangfacheng on 2017-11-13.
 */
public class UserOperateException extends RuntimeException {
    
    public UserOperateException() {
        super();
    }

    public UserOperateException(String message) {
        super(message);
    }

    public UserOperateException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserOperateException(Throwable cause) {
        super(cause);
    }

    protected UserOperateException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
