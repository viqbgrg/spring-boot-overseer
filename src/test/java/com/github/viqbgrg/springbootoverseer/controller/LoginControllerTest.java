package com.github.viqbgrg.springbootoverseer.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.viqbgrg.springbootoverseer.domain.dto.UserSignInDto;
import com.github.viqbgrg.springbootoverseer.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(LoginController.class)
public class LoginControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserService userService;

    @Autowired
    ObjectMapper objectMapper;

    /**
     * 正常注册
     * 用户名不存在
     * 密码符合规则
     *
     * @throws Exception
     */
    @Test
    void signIn() throws Exception {
        UserSignInDto users = new UserSignInDto();
//        users.setUsername("xiaoming");
        users.setPassword("123456");
        users.setEmail("11111@qq.com");
        when(userService.signIn(users)).thenReturn(true);
        this.mvc.perform(post("/signIn").contentType(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(users)))
                .andDo(print()).andExpect(status().isCreated());
    }

    // 用户名, 密码不符合规则 验证 然后统一异常去返回前台数据
    @Test
    void signInValidatedTest() {
        UserSignInDto user = new UserSignInDto();
        user.setUsername("xiao");
        user.setPassword("123");
        when(userService.signIn(user)).thenReturn(true);
    }
    // todo 用户名已注册
    // TODO: 2020/8/27 027 登陆成功, 前端接收 token
    // TODO: 2020/8/27 027 用户名和密码错误


    @Test
    void login() {
    }

}
