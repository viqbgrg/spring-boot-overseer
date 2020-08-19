package com.github.viqbgrg.springbootoverseer.shiro;

import com.github.viqbgrg.springbootoverseer.entity.User;
import com.github.viqbgrg.springbootoverseer.service.UserService;
import com.github.viqbgrg.springbootoverseer.service.impl.UserServiceImpl;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.DefaultPasswordService;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.subject.Subject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {UserServiceImpl.class, UserRealm.class})
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserRealmTest {

    @MockBean
    private UserService userService;

    @Autowired
    private UserRealm userRealm;
    private DefaultPasswordService defaultPasswordService;
    private Subject subject;

    @BeforeAll
    void beforeAll() {
        DefaultSecurityManager securityManager = new DefaultSecurityManager();
        securityManager.setRealm(userRealm);
        SecurityUtils.setSecurityManager(securityManager);
        subject = SecurityUtils.getSubject();
        defaultPasswordService = new DefaultPasswordService();
    }

    // 密码正确 有customer权限 没有admin权限
    @Test
    void testLogin() {
        UsernamePasswordToken token = new UsernamePasswordToken("zhang", "123");
        String s = defaultPasswordService.encryptPassword("123");
        User user = new User();
        user.setUsername("zhang");
        user.setPassword(s);
        when(userService.findUserByUsername("zhang")).thenReturn(user);
        subject.login(token);
        assertThat(subject.isAuthenticated()).isTrue();
        assertThat(subject.hasRole(UserRealm.CUSTOME_ROLE)).isTrue();
        assertThat(subject.hasRole(UserRealm.ADMIN_ROLE)).isFalse();
        subject.logout();
    }

    @Test
    void testLoginNoUsername() {
        UsernamePasswordToken token = new UsernamePasswordToken("zhang1", "123");
        when(userService.findUserByUsername("zhang1")).thenReturn(null);
        assertThatThrownBy(() -> {
            subject.login(token);
        }).isInstanceOf(UnknownAccountException.class);
    }

    @Test
    void testLoginPasswordError() {
        UsernamePasswordToken token = new UsernamePasswordToken("zhang", "1231");
        String s = defaultPasswordService.encryptPassword("123");
        User user = new User();
        user.setUsername("zhang");
        user.setPassword(s);
        when(userService.findUserByUsername("zhang")).thenReturn(user);
        assertThatThrownBy(() -> {
            subject.login(token);
        }).isInstanceOf(IncorrectCredentialsException.class);
    }

    @Test
    void testLoginAdmin() {
        UsernamePasswordToken token = new UsernamePasswordToken("admin", "123");
        String s = defaultPasswordService.encryptPassword("123");
        User user = new User();
        user.setUsername("admin");
        user.setPassword(s);
        when(userService.findUserByUsername("admin")).thenReturn(user);
        subject.login(token);
        assertThat(subject.isAuthenticated());
        subject.logout();
    }
}
