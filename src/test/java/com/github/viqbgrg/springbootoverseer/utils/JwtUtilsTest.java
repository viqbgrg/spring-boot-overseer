package com.github.viqbgrg.springbootoverseer.utils;

import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

class JwtUtilsTest {
    private String username = "xiaoming";
    private String salt = "12345";

    @Test
    void sign() {
        String token = JwtUtils.sign(username, salt, 10);
        assertThat(token).isNotEmpty();
    }

    @Test
    void verify() {
        String token = JwtUtils.sign(username, salt, 10);
        assertThat(JwtUtils.verify(token, username, salt)).isTrue();
        assertThat(JwtUtils.verify(token, username + 1, salt)).isFalse();
        assertThat(JwtUtils.verify(token, username, salt + 1)).isFalse();
    }

    @Test
    void getClaim() {
        String token = JwtUtils.sign(username, salt, 10);
        assertThat(JwtUtils.getClaim(token, "username")).isEqualTo(username);
    }

    @Test
    void getIssuedAt() {
        String token = JwtUtils.sign(username, salt, 10);
        assertThat(JwtUtils.getIssuedAt(token)).isBefore(new Date());
    }

    @Test
    void isTokenExpired() throws InterruptedException {
        String token = JwtUtils.sign(username, salt, 10);
        assertThat(JwtUtils.isTokenExpired(token)).isFalse();
        Thread.sleep(10000);
        assertThat(JwtUtils.isTokenExpired(token)).isTrue();
    }
}