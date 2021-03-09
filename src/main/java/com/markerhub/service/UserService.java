package com.markerhub.service;

import com.markerhub.common.dto.LoginDTO;
import com.markerhub.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ce
 * @since 2021-03-03
 */
public interface UserService extends IService<User> {

    boolean login(LoginDTO loginDTO, HttpServletResponse response);
}
