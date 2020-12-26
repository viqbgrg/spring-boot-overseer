package com.github.viqbgrg.springbootoverseer.properties;

import com.github.viqbgrg.springbootoverseer.user.entity.User;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class TestUserProperties {

    @Bean
    @ConfigurationProperties("user")
    public User customUser() {
        return new User();
    }
    @Bean
    @ConfigurationProperties("admin-user")
    public User adminUser() {
        return new User();
    }
}
