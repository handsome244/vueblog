package com.markerhub.utils;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.URLUtil;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

/**
 * Servlet 工具类
 *
 * @author shenxr
 * @date 2020-06-19 11:40
 * @description
 **/
public abstract class ServletUtils {
    public static final String TOKEN_KEY = "token";

    /**
     * 获取当前Request对象
     *
     * @return Request对象
     */
    public static Optional<HttpServletRequest> getRequest() {
        ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return attrs == null ? Optional.empty() : Optional.of(attrs.getRequest());
    }

    public static void fileDownloadHeader(HttpServletResponse response, String fileName) {
        response.setHeader("Content-Disposition", "attachment;fileName=" + URLUtil.encode(fileName));
        response.setHeader("Access-Control-Expose-Headers", "filename");
        response.setHeader("filename", URLUtil.encode(fileName));
    }

    /**
     * 获取请求的token
     */
    public static String getRequestToken() {
        Optional<HttpServletRequest> optional = getRequest();
        return optional.map(request -> getParam(request, TOKEN_KEY)).orElse(null);
    }

    public static String getParam(HttpServletRequest request, String key) {
        //  从header中获取token
        String token = request.getHeader(key);
        //      如果header中不存在token，则从参数中获取token
        if (StrUtil.isBlank(token)) {
            token = request.getParameter(key);
        }
        //  从cookie获取
        if (StrUtil.isBlank(token)) {
            Cookie[] cookies = request.getCookies();
            if (ArrayUtil.isNotEmpty(cookies)) {
                for (Cookie cookie : cookies) {
                    if (key.equals(cookie.getName())) {
                        token = cookie.getValue();
                    }
                }
            }
        }
        return token;
    }

//    public static Optional<RequestInfo> getAppInfo() {
//        return getRequest().map(request -> {
//            String application = request.getHeader(AppInterceptor.APPLICATION_KEY);
//            String platform = request.getHeader(AppInterceptor.APP_PLATFORM_KEY);
//            String version = request.getHeader(AppInterceptor.APP_VERSION_KEY);
//            String uri = request.getRequestURI();
//            Long userId = UserUtils.getUserId().orElse(null);
//            return new RequestInfo(application, platform, version, uri, userId);
//        });
//    }
}
