package com.github.viqbgrg.springbootoverseer.controller;

import com.github.viqbgrg.springbootoverseer.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(LoginController.class)
class LoginControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserService userService;

    @Test
    void signIn() throws Exception {
        this.mvc.perform(get("/signIn").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(content().string("Honda Civic"));
    }

    @Test
    void login() {
    }
}