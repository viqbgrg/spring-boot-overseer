package com.github.viqbgrg.springbootoverseer.user.controller;

import com.github.viqbgrg.springbootoverseer.domain.dto.UserLoginDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class UserLoginControllerTest {

    @Autowired
    TestRestTemplate restTemplate;


    @Test
    void login() {
        UserLoginDto userLoginDto = new UserLoginDto();
        userLoginDto.setUsername("username");
        userLoginDto.setPassword("123456");
        HttpEntity<UserLoginDto> userLoginDtoHttpEntity = new HttpEntity<>(userLoginDto);
        ResponseEntity<Void> responseEntityResponseEntity = this.restTemplate.postForEntity("/user/login", userLoginDtoHttpEntity, Void.class);
        HttpHeaders headers = responseEntityResponseEntity.getHeaders();
        assertThat(responseEntityResponseEntity.getStatusCodeValue()).isEqualTo(HttpStatus.OK.value());
        assertThat(headers.getFirst("authorization")).isNotEmpty();
    }

    @Test
    void loginUserPasswordError() {
        UserLoginDto userLoginDto = new UserLoginDto();
        userLoginDto.setUsername("username" + "error");
        userLoginDto.setPassword("123456");
        HttpEntity<UserLoginDto> userLoginDtoHttpEntity = new HttpEntity<>(userLoginDto);
        ResponseEntity<Void> responseEntityResponseEntity = this.restTemplate.postForEntity("/user/login", userLoginDtoHttpEntity, Void.class);
        HttpHeaders headers = responseEntityResponseEntity.getHeaders();
        assertThat(responseEntityResponseEntity.getStatusCodeValue()).isEqualTo(HttpStatus.OK.value());
        assertThat(headers.getFirst("authorization")).isNotEmpty();
    }
}
