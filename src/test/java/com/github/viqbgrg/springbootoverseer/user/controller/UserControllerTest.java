package com.github.viqbgrg.springbootoverseer.user.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.viqbgrg.springbootoverseer.domain.dto.UserSignInDto;
import com.github.viqbgrg.springbootoverseer.exception.UsernameExistException;
import com.github.viqbgrg.springbootoverseer.user.entity.User;
import com.github.viqbgrg.springbootoverseer.user.service.IUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.zalando.problem.spring.web.autoconfigure.ProblemAutoConfiguration;
import org.zalando.problem.spring.web.autoconfigure.ProblemJacksonAutoConfiguration;
import org.zalando.problem.spring.web.autoconfigure.ProblemJacksonWebMvcAutoConfiguration;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
@ImportAutoConfiguration(classes = {ProblemAutoConfiguration.class, ProblemJacksonAutoConfiguration.class
        , ProblemJacksonWebMvcAutoConfiguration.class
})
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
        User users = new User();
        BeanUtils.copyProperties(userSignInDto, users);
        when(userService.signIn(users)).thenReturn(true);
        this.mvc.perform(post("/user/signIn").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsBytes(userSignInDto)))
                .andExpect(status().isCreated());
    }

    /**
     * 用户名已存在
     */
    @Test
    void signInErrorUsernameExist() throws Exception {
        UserSignInDto userSignInDto = new UserSignInDto();
        userSignInDto.setUsername("username");
        userSignInDto.setPassword("123456");
        userSignInDto.setEmail("123456@qq.com");
        User users = new User();
        BeanUtils.copyProperties(userSignInDto, users);
        when(userService.signIn(users)).thenThrow(new UsernameExistException(users.getUsername()));
        this.mvc.perform(post("/user/signIn").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsBytes(userSignInDto)))
                .andDo(print()).andExpect(status().isBadRequest());
    }
}