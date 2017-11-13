package spring.demo.exception;

/**
 * Created by wangfacheng on 2017-11-13.
 */
public class MenuOperateException extends RuntimeException {

    public MenuOperateException() {
        super();
    }

    public MenuOperateException(String message) {
        super(message);
    }

    public MenuOperateException(String message, Throwable cause) {
        super(message, cause);
    }

    public MenuOperateException(Throwable cause) {
        super(cause);
    }

    protected MenuOperateException(String message, Throwable cause, boolean enableSuppression,
            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
