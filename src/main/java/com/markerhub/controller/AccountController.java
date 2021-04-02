package com.markerhub.controller;

import com.markerhub.common.dto.LoginDTO;
import com.markerhub.common.lang.Result;
import com.markerhub.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * @author Admin
 * @Description
 * @date 2021/3/9 16:22
 */
@RestController
@RequestMapping("/sys")
public class AccountController {

    @Autowired
    UserService userService;

    @PostMapping("/login")
    public Result login(@Validated @RequestBody LoginDTO loginDTO, HttpServletResponse response){
        return Result.success(userService.login(loginDTO, response));
    }

    @RequiresAuthentication
    @PostMapping("/logout")
    public Result logout(){
        SecurityUtils.getSubject().logout();
        return Result.success(null);
    }
}
