package com.github.viqbgrg.springbootoverseer.user.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.viqbgrg.springbootoverseer.domain.dto.UserSignInDto;
import com.github.viqbgrg.springbootoverseer.user.service.IUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private IUserService userService;

    /**
     * 用户正常注册
     */
    @Test
    void signIn() throws Exception {
        UserSignInDto userSignInDto = new UserSignInDto();
        userSignInDto.setUsername("username");
        userSignInDto.setPassword("123456");
        userSignInDto.setEmail("123456@qq.com");
        this.mvc.perform(post("/user/signIn").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsBytes(userSignInDto)))
                .andExpect(status().isCreated());
    }

    /**
     * 用户名已存在
     */
}