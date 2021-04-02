package com.markerhub.shiro;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @author Admin
 * @Description
 * @date 2021/3/3 17:03
 */
public class JwtToken implements AuthenticationToken {
    private String token;
    public JwtToken(String token) {
        this.token = token;
    }
    @Override
    public Object getPrincipal() {
        return token;
    }
    @Override
    public Object getCredentials() {
        return token;
    }
}

