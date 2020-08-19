package com.github.viqbgrg.springbootoverseer.utils;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class JwtUtilsTest {

    @Test
    void sign() {
        String username = "xiaoming";
        String token = JwtUtils.sign("user", "12345", 10);
        assertThat(token).isNotEmpty();
    }
}