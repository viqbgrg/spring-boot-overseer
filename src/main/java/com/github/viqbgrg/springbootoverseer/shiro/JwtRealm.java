package com.github.viqbgrg.springbootoverseer.shiro;

import com.github.viqbgrg.springbootoverseer.model.User;
import com.github.viqbgrg.springbootoverseer.user.service.IUserService;
import com.github.viqbgrg.springbootoverseer.utils.JwtUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

public class JwtRealm extends AuthorizingRealm {

    private IUserService userService;

    public JwtRealm(JwtCredentialsMatcher jwtCredentialsMatcher, IUserService userService) {
        this.setCredentialsMatcher(jwtCredentialsMatcher);
        this.userService = userService;
    }

    @Override
    public String getName() {
        return "jwtRealm";
    }

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtAuthenticationToken;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.addRole(UserRealm.CUSTOME_ROLE);
        User user = (User) principals.getPrimaryPrincipal();
        // 只有 admin 账号具有管理员权限
        if (UserRealm.ADMIN_ROLE.equals(user.getUsername())) {
            simpleAuthorizationInfo.addRole(UserRealm.ADMIN_ROLE);
        }
        return simpleAuthorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        JwtAuthenticationToken jwtAuthenticationToken = (JwtAuthenticationToken) token;
        String jwtToken = (String) jwtAuthenticationToken.getPrincipal();
        if (JwtUtils.isTokenExpired(jwtToken)) {
            throw new AuthenticationException("token过期，请重新登录");
        }
        String username = JwtUtils.getClaim(jwtToken, "username");
        User userByUsername = userService.findUserByUsername(username);
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(userByUsername, "userByUsername.getSalt()", "jwtRealm");
        return authenticationInfo;
    }
}
