package com.github.viqbgrg.springbootoverseer.shiro;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @author viqbgrg
 */
public class JwtAuthenticationToken implements AuthenticationToken {
    private String token;
    public JwtAuthenticationToken(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return this.token;
    }

    @Override
    public Object getCredentials() {
        return this.token;
    }
}
