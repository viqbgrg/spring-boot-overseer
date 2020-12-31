package com.github.viqbgrg.springbootoverseer.shiro;

import com.github.viqbgrg.springbootoverseer.entity.User;
import com.github.viqbgrg.springbootoverseer.service.IUserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.PasswordMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

public class UserRealm extends AuthorizingRealm {
    public static final String ADMIN_ROLE = "admin";
    public static final String CUSTOME_ROLE = "customer";
    private IUserService userService;

    public UserRealm(IUserService userService) {
        PasswordMatcher passwordMatcher = new PasswordMatcher();
        this.setCredentialsMatcher(passwordMatcher);
        this.userService = userService;
    }

    @Override
    public String getName() {
        return "userRealm";
    }

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof UsernamePasswordToken;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.addRole(CUSTOME_ROLE);
        User user = (User) principals.getPrimaryPrincipal();
        // 只有 admin 账号具有管理员权限
        if (ADMIN_ROLE.equals(user.getUsername())) {
            simpleAuthorizationInfo.addRole(ADMIN_ROLE);
        }
        return simpleAuthorizationInfo;
    }


    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken userpasswordToken = (UsernamePasswordToken) token;
        String username = userpasswordToken.getUsername();
        User user = userService.findUserByUsername(username);
        if (user == null) {
            throw new UnknownAccountException();
        }
        return new SimpleAuthenticationInfo(user, user.getPassword(), this.getName());
    }
}
