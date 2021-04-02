package com.markerhub.common.exception;

/**
 * 拒绝访问异常
 *
 * @author shenxr
 * @date 2020-07-01 17:18
 * @description
 **/
public class AccessDeniedException extends RuntimeException {
    private static final long serialVersionUID = -6211776844269150731L;

    public AccessDeniedException() {
        super();
    }

    public AccessDeniedException(String message) {
        super(message);
    }

    public AccessDeniedException(String message, Throwable cause) {
        super(message, cause);
    }

    public AccessDeniedException(Throwable cause) {
        super(cause);
    }

    public AccessDeniedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
