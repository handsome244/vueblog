package com.markerhub.common.exception;

import com.markerhub.common.lang.Result;
import com.markerhub.utils.ServletUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author Admin
 * @Description
 * @date 2021/3/9 14:50
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AccessDeniedException.class)
    public Result<?> handleAccessDeniedException(AccessDeniedException e) {
        String url = ServletUtils.getRequest().map(request -> request.getRequestURL().toString()).orElse(null);
        log.warn("授权失败，URL：" + url, e);
        return Result.error(e.getMessage(), HttpStatus.UNAUTHORIZED);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = RuntimeException.class)
    public Result<?> handler(RuntimeException e) {
        log.error("运行时异常----------------{}", e);
        return Result.error(e.getMessage());
    }
}
