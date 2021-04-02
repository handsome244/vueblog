package com.markerhub.common.exception;

import cn.hutool.core.util.StrUtil;
import com.markerhub.common.constant.ErrorCode;

import static com.markerhub.common.lang.Result.COMMON_ERROR_CODE;

/**
 * 自定义异常
 *
 * @author admin
 */
public class BusinessException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private Integer code = COMMON_ERROR_CODE;

    public BusinessException(String msg) {
        super(msg);
    }

    public BusinessException(String msg, Object... args) {
        super(StrUtil.format(msg, args));
    }

    public BusinessException(String msg, Throwable e) {
        super(msg, e);
    }

    public BusinessException(String msg, int code) {
        super(msg);
        this.code = code;
    }

    public BusinessException(String msg, int code, Throwable e) {
        super(msg, e);
        this.code = code;
    }

    public BusinessException(ErrorCode code) {
        super(code.getMessage());
        this.code = code.getCode();
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }


}
