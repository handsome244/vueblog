package com.markerhub.utils;

import com.markerhub.common.exception.AccessDeniedException;
import com.markerhub.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import java.util.Optional;

/**
 * 用户工具类
 *
 * @author admin
 */
@Slf4j
public abstract class UserUtils {

    public static Optional<Subject> getSubject() {
        Subject subject;
        try {
            subject = SecurityUtils.getSubject();
            return Optional.ofNullable(subject);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public static Optional<User> getUser() {
        User userEntity = (User) getSubject().map(Subject::getPrincipal).orElse(null);
        return Optional.ofNullable(userEntity);
    }

    public static User getUserNotNull() {
        return getUser().orElseThrow(() -> new AccessDeniedException("未登录"));
    }

    public static Optional<Long> getUserId() {
        return getUser().map(User::getId);
    }

    public static Long getUserIdNotNull() {
        return getUserId().orElseThrow(() -> new AccessDeniedException("未登录"));
    }


}
