package com.github.viqbgrg.springbootoverseer.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void login() {
        ResponseEntity responseEntity = restTemplate.postForEntity("/api/account/sign_in", null, String.class);
        System.out.println(responseEntity.getBody());
    }
}