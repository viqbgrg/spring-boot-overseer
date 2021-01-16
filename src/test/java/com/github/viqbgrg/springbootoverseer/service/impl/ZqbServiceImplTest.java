package com.github.viqbgrg.springbootoverseer.service.impl;

import com.github.viqbgrg.springbootoverseer.config.TestConfig;
import com.github.viqbgrg.springbootoverseer.domain.dto.XunleiAccountDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.ConfigDataApplicationContextInitializer;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

//@Import({TestConfig.class, ZqbServiceImpl.class})
@ActiveProfiles("test")
//@SpringBootTest
@ContextConfiguration(classes = {TestConfig.class}, initializers = ConfigDataApplicationContextInitializer.class)
@ExtendWith({MockitoExtension.class, SpringExtension.class})
class ZqbServiceImplTest {
    @Autowired
    private XunleiAccountDto xunleiAccountDto;

    @Test
    void login() {
        assertThat(xunleiAccountDto.getUsername()).isNotNull();
    }
}