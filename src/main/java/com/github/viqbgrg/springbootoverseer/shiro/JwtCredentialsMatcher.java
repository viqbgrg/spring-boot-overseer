package com.github.viqbgrg.springbootoverseer.shiro;

import com.github.viqbgrg.springbootoverseer.user.entity.User;
import com.github.viqbgrg.springbootoverseer.utils.JwtUtils;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.springframework.stereotype.Component;

/**
 * @author viqbgrg
 */
@Component
public class JwtCredentialsMatcher implements CredentialsMatcher {

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        String jwtToken = (String) token.getPrincipal();
        User user = (User) info.getPrincipals().getPrimaryPrincipal();
        String salt = (String) info.getCredentials();
        return JwtUtils.verify(jwtToken, user.getUsername(), salt);
    }
}
