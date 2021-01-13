package com.github.viqbgrg.springbootoverseer.service.impl;

import com.github.viqbgrg.springbootoverseer.entity.User;
import com.github.viqbgrg.springbootoverseer.exception.UsernameExistException;
import com.github.viqbgrg.springbootoverseer.service.IUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
class UserServiceImplTest {

    @Autowired
    private IUserService userService;

    @Test
    void findUserByUsername() {
        User user = this.userService.findUserByUsername("username");
        assertThat(user).isNotNull();
    }

    @Test
    void signIn() {
        User user = new User();
        user.setUsername("username1");
        user.setPassword("123456");
        user.setEmail("123456@qq.com");
        user.setLocked(0);
        user.setCreateAt(LocalDateTime.now());
        assertThat(userService.signIn(user)).isTrue();
    }

    @Test
    void signInUsernameExist() {
        User user = new User();
        user.setUsername("username");
        user.setPassword("123456");
        user.setEmail("123456@qq.com");
        user.setLocked(0);
        user.setCreateAt(LocalDateTime.now());
        assertThatThrownBy(() -> userService.signIn(user)).isInstanceOf(UsernameExistException.class);
    }
}