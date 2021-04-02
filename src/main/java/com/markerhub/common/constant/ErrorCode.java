package com.markerhub.common.constant;

import cn.hutool.http.HttpStatus;
import lombok.Getter;

import static com.markerhub.common.lang.Result.*;

/**
 * @author shenxr
 * @date 2020-08-13 19:58
 * @description
 **/
@Getter
public enum ErrorCode {
    /**
     * 错误码
     */
    SUCCESS(SUCCESS_CODE, SUCCESS_MESSAGE),       //      成功
    COMMON_ERROR(COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE),       //      通用失败
    UNAUTHORIZED(HttpStatus.HTTP_UNAUTHORIZED, "登录过期"),       //      通用失败
    REGISTER_STUDENT_EXITS(10001, "身份证已存在但手机号码不一致"),
    SCHOOL_ONLINE_PAY_NOT_SUPPORT(10002, "该驾校暂不支持在线支付，请到线下缴费"),
    ;


    private final Integer code;
    private final String message;

    ErrorCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
