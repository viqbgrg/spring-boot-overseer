package com.github.viqbgrg.springbootoverseer.shiro;

import com.github.viqbgrg.springbootoverseer.entity.User;
import com.github.viqbgrg.springbootoverseer.service.UserService;
import com.github.viqbgrg.springbootoverseer.service.impl.UserServiceImpl;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.DefaultPasswordService;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.subject.Subject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {UserServiceImpl.class, UserRealm.class})
public class UserRealmTest {

    @MockBean
    private UserService userService;

    @Autowired
    private UserRealm userRealm;

    // 密码正确
    @Test
    void login() {
        DefaultSecurityManager securityManager = new DefaultSecurityManager();
        securityManager.setRealm(userRealm);
        SecurityUtils.setSecurityManager(securityManager);
        Subject subject = SecurityUtils.getSubject();
        DefaultPasswordService defaultPasswordService = new DefaultPasswordService();
        String s = defaultPasswordService.encryptPassword("123");
        UsernamePasswordToken token = new UsernamePasswordToken("zhang", "123");
        User user = new User();
        user.setUsername("zhang");
        user.setPassword(s);
        when(userService.findUserByUsername("zhang")).thenReturn(user);
        subject.login(token);
        assertThat(subject.isAuthenticated());
    }

    // todo 用户不存在


    //todo 密码正确

    //todo 密码错误

    //todo username 为admin的账号 拥有 admin customer 的权限

    // todo 其它的用户只有customer的权限
}
