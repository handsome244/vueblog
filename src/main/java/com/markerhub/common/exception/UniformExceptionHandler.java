package com.markerhub.common.exception;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.markerhub.common.lang.Result;
import com.markerhub.utils.DateUtils;
import com.markerhub.utils.PatternUtils;
import com.markerhub.utils.ServletUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.beans.PropertyEditorSupport;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 统一异常处理器
 *
 * @author admin
 */
@Slf4j
@RestControllerAdvice
public class UniformExceptionHandler {
    public static final ThreadLocal<Boolean> LOG_FLAG = new ThreadLocal<Boolean>();

    /**
     * 处理自定义异常
     */
    @ExceptionHandler(BusinessException.class)
    public Result<?> handleBusinessException(BusinessException e) {
        Throwable cause = e.getCause();
        if (cause == null) {
            log.info("业务异常：" + e.getMessage());
        } else {
            log.error("业务异常：" + e.getMessage(), cause);
        }
        return Result.error(e.getMessage(), e.getCode());
    }

    @ExceptionHandler(AccessDeniedException.class)
    public Result<?> handleAccessDeniedException(AccessDeniedException e) {
        String url = ServletUtils.getRequest().map(request -> request.getRequestURL().toString()).orElse(null);
        log.warn("授权失败，URL：" + url, e);
        return Result.error(e.getMessage(), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public Result<?> handleNoFoundException(NoHandlerFoundException e) {
        log.warn("路径不存在异常：" + e.getMessage());
        return Result.error("路径不存在，请检查路径是否正确", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public Result<?> handleMissingServletRequestParameterException(MissingServletRequestParameterException e) {
        log.warn("参数[" + e.getParameterName() + "]缺失");
        return Result.error("参数[" + e.getParameterName() + "]缺失", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DuplicateKeyException.class)
    public Result<?> handleDuplicateKeyException(DuplicateKeyException e) {
        log.warn("数据重复异常：" + e.getMessage(), e);
        return Result.error("数据已存在");
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public Result<?> handleDuplicateKeyException(IllegalArgumentException e) {
        log.warn("参数异常：" + e.getMessage());
        String message = e.getMessage();
        if (StrUtil.isBlank(message) || message.length() > 72 || !PatternUtils.isContainChinese(message)) {
            log.error(message, e);
            message = "参数错误";
        }
        return Result.error(message);
    }

    @ExceptionHandler(AuthorizationException.class)
    public Result<?> handleAuthorizationException(AuthorizationException e) {
        log.warn("授权失败异常：{}", e.getMessage());
        return Result.error("没有权限，请联系管理员授权");
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        List<ObjectError> allErrors = e.getBindingResult().getAllErrors();
        if (CollUtil.isNotEmpty(allErrors)) {
            List<String> errorMessages = allErrors.stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.toList());
            return Result.error(CollUtil.join(errorMessages, "、"));
        } else {
            return Result.error("参数错误");
        }
    }

//    @ExceptionHandler(Exception.class)
//    public Result<?> handleException(Exception e) {
//        try {
//            if (!Boolean.TRUE.equals(LOG_FLAG.get())) {
//                String message = "未知异常，" + ServletUtils.getAppInfo().map(RequestInfo::toString).orElse(StrUtil.EMPTY);
//                log.error(message, e);
//            }
//        } finally {
//            LOG_FLAG.remove();
//        }
//        return Result.error();
//    }

    /**
     * 日期类型处理
     *
     * @param binder
     */
    @InitBinder
    public void dataBind(WebDataBinder binder) {
        binder.registerCustomEditor(Date.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) throws IllegalArgumentException {
                setValue(StrUtil.isNotBlank(text) ? DateUtils.parse(text) : null);
            }
        });
    }

}
