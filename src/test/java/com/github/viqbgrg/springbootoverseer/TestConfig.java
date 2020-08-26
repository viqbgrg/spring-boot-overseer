package com.github.viqbgrg.springbootoverseer;

import com.github.viqbgrg.springbootoverseer.entity.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestConfig {

    @Bean
    public User customUser() {
        User user = new User();
        user.setUsername("xiaoming");
        user.setPassword("12345");
        user.setSalt("salt");
        return user;
    }
}
