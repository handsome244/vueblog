package com.markerhub.common.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @author Admin
 * @Description
 * @date 2021/3/9 16:20
 */
@Data
public class LoginDTO {

    @NotEmpty(message = "用户名不能为空!")
    private String username;

    @NotEmpty(message = "密码不能为空!")
    private String password;
}
