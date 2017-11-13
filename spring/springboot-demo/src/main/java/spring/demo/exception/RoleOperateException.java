package spring.demo.exception;

/**
 * Created by feng on 17/11/13.
 */
public class RoleOperateException extends RuntimeException {
    
    public RoleOperateException() {
        super();
    }

    public RoleOperateException(String message) {
        super(message);
    }

    public RoleOperateException(String message, Throwable cause) {
        super(message, cause);
    }

    public RoleOperateException(Throwable cause) {
        super(cause);
    }

    protected RoleOperateException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
