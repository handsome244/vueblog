package com.markerhub.service;

import com.markerhub.common.dto.LoginDTO;
import com.markerhub.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ce
 * @since 2021-03-03
 */
public interface UserService extends IService<User> {

    /**
     * 用户登录
     * @param loginDTO
     * @param response
     * @return
     */
    Map<Object, Object> login(LoginDTO loginDTO, HttpServletResponse response);
}
