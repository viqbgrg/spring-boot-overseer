package com.github.viqbgrg.springbootoverseer.controller;

import com.github.viqbgrg.springbootoverseer.domain.dto.UserLoginDto;
import com.github.viqbgrg.springbootoverseer.domain.vo.UserInfoVo;
import com.github.viqbgrg.springbootoverseer.utils.JwtUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

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
        ResponseEntity<String> responseEntityResponseEntity = this.restTemplate.postForEntity("/user/login", userLoginDtoHttpEntity, String.class);
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
        ResponseEntity<UserController.JWTToken> responseEntityResponseEntity = this.restTemplate.postForEntity("/user/login", userLoginDtoHttpEntity, UserController.JWTToken.class);
        HttpHeaders headers = responseEntityResponseEntity.getHeaders();
        assertThat(responseEntityResponseEntity.getStatusCodeValue()).isEqualTo(HttpStatus.OK.value());
        assertThat(headers.getFirst("authorization")).isNotEmpty();
    }

    /**
     * 使用 jwt token 正常访问
     */
    @Test
    void logoutTest() {
        String jwt = "Bearer " + JwtUtils.sign("username", "123456");
        HttpHeaders headers = new HttpHeaders();
        headers.add("authorization", jwt);
        ResponseEntity<UserInfoVo> result = this.restTemplate.exchange("/user/logout", HttpMethod.GET, new HttpEntity<>(headers), UserInfoVo.class);
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
    }



    /**
     *  使用 jwt token 过期
     */
}
