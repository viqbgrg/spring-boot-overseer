package com.github.viqbgrg.springbootoverseer.config;

import com.github.viqbgrg.springbootoverseer.domain.dto.XunleiAccountDto;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestConfig {

    @Bean
    @ConfigurationProperties(prefix = "xunlei")
    public XunleiAccountDto getAccountDto() {
        return new XunleiAccountDto();
    }
}
