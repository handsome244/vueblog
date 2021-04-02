package com.markerhub.common.exception;

/**
 * @author shenxr
 * @date 2020-09-04 15:20
 * @description
 **/
public class RequestException extends BusinessException {
    private static final long serialVersionUID = -7778229521784311469L;

    public RequestException(String msg) {
        super(msg);
    }

    public RequestException(String msg, Object... args) {
        super(msg, args);
    }

    public RequestException(String msg, Throwable e) {
        super(msg, e);
    }

    public RequestException(String msg, int code) {
        super(msg, code);
    }

    public RequestException(String msg, int code, Throwable e) {
        super(msg, code, e);
    }
}
