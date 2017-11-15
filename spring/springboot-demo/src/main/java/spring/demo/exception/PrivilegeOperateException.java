package spring.demo.exception;

/**
 * Created by wangfacheng on 2017-11-13.
 */
public class PrivilegeOperateException extends RuntimeException {

    public PrivilegeOperateException() {
        super();
    }

    public PrivilegeOperateException(String message) {
        super(message);
    }

    public PrivilegeOperateException(String message, Throwable cause) {
        super(message, cause);
    }

    public PrivilegeOperateException(Throwable cause) {
        super(cause);
    }

    protected PrivilegeOperateException(String message, Throwable cause, boolean enableSuppression,
            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
