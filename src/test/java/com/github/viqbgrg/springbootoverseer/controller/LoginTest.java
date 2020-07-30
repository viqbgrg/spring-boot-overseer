package com.github.viqbgrg.springbootoverseer.controller;

import com.github.viqbgrg.springbootoverseer.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LoginTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @MockBean
    private UserService userService;

    @Test
    void login(){
        String body = restTemplate.getForEntity("/login", String.class).getBody();
        assertThat(body).isEqualTo("Hello World");
    }
}
