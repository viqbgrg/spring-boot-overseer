package com.github.viqbgrg.springbootoverseer.controller;

import com.github.viqbgrg.springbootoverseer.entity.User;
import com.github.viqbgrg.springbootoverseer.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @MockBean
    private UserService userService;

    @Test
    void login() {
        String body = restTemplate.getForEntity("/login", String.class).getBody();
        assertThat(body).isEqualTo("Hello World");
    }

    @Test
    void signIn() {
        User user = new User();
        user.setUsername("xiaoming");
        user.setPassword("123456");
        when(userService.addUser(user)).thenReturn(true);
        ResponseEntity responseEntity = restTemplate.postForEntity("/signIn", user, Void.class);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }

    // todo 用户名, 密码不符合规则 验证 然后统一异常去返回前台数据
    // todo 用户名已注册
    // TODO: 2020/8/27 027 登陆成功, 前端接收 token
    // TODO: 2020/8/27 027 用户名和密码错误

}