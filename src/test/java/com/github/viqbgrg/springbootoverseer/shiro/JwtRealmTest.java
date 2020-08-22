package com.github.viqbgrg.springbootoverseer.shiro;

import com.github.viqbgrg.springbootoverseer.entity.User;
import com.github.viqbgrg.springbootoverseer.service.UserService;
import com.github.viqbgrg.springbootoverseer.service.impl.UserServiceImpl;
import com.github.viqbgrg.springbootoverseer.utils.JwtUtils;
import org.apache.shiro.SecurityUtils;
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
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {UserServiceImpl.class, JwtRealm.class, JwtCredentialsMatcher.class})
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class JwtRealmTest {
    private User user;
    @Autowired
    private JwtRealm jwtRealm;

    @MockBean
    private UserService userService;

    private Subject subject;

    @BeforeAll
    public void geUser() {
        User user = new User();
        user.setUsername("zhang");
        user.setPassword("123456");
        user.setSalt("2125453465");
        this.user = user;
        DefaultSecurityManager securityManager = new DefaultSecurityManager();
        securityManager.setRealm(jwtRealm);
        SecurityUtils.setSecurityManager(securityManager);
        subject = SecurityUtils.getSubject();
        when(userService.findUserByUsername("zhang")).thenReturn(user);
    }

    // token 正确
    @Test
    void reLogin() {
        String token = JwtUtils.sign(user.getUsername(), user.getSalt(), 1000);
        JwtAuthenticationToken jwtAuthenticationToken = new JwtAuthenticationToken(token);
        subject.login(jwtAuthenticationToken);
        assertThat(subject.isAuthenticated()).isTrue();
        assertThat(subject.hasRole(UserRealm.CUSTOME_ROLE)).isTrue();
        assertThat(subject.hasRole(UserRealm.ADMIN_ROLE)).isFalse();
        subject.logout();
    }
}
