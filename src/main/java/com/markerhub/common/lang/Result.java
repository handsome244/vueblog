package com.markerhub.common.lang;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

/**
 * @author Admin
 * @Description
 * @date 2021/3/3 15:12
 */
@Getter
@Setter
public class Result<T> implements Serializable {
    public static final Integer SUCCESS_CODE = 0;
    public static final String SUCCESS_MESSAGE = "SUCCESS";

    public static final Integer COMMON_ERROR_CODE = 10000;
    public static final String COMMON_ERROR_MESSAGE = "操作失败";

    @ApiModelProperty("错误码（0-成功，其他失败）")
    private Integer code;

    @ApiModelProperty("错误消息")
    private String message;

    @ApiModelProperty("数据")
    private T data;

    @ApiModelProperty("是否成功")
    public boolean isSuccess() {
        return SUCCESS_CODE.equals(code);
    }

    public static <T> Result<T> success(T data) {
        Result<T> result = new Result<>();
        result.setCode(SUCCESS_CODE);
        result.setMessage(SUCCESS_MESSAGE);
        result.setData(data);
        return result;
    }

    public static <T> Result<T> success() {
        return success(null);
    }

    public static <T> Result<T> error(String message, Integer code, T data) {
        Result<T> result = new Result<>();
        result.setMessage(message);
        result.setCode(code);
        result.setData(data);
        return result;
    }

    public static <T> Result<T> error(String message, Integer code) {
        return error(message, code, null);
    }

    public static <T> Result<T> error(String message, HttpStatus status) {
        return error(message, status.value());
    }

    public static <T> Result<T> error(String message) {
        return error(message, COMMON_ERROR_CODE);
    }

    public static <T> Result<T> error() {
        return error(COMMON_ERROR_MESSAGE);
    }

    public static <T> Result<T> result(boolean success) {
        return success ? success() : error();
    }
}

