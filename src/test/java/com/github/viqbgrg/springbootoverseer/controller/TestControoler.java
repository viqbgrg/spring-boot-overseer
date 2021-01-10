package com.github.viqbgrg.springbootoverseer.controller;

import com.github.viqbgrg.springbootoverseer.domain.dto.XunleiAccountDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
public class TestControoler {
    @Autowired
    private XunleiAccountDto dto;

    @Autowired
    private Environment environment;

    @Test
    void beanTest() {
        assertThat(environment).isNotNull();
        assertThat(dto).isNotNull();
    }
}
