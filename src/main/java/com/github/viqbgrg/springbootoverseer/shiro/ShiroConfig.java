package com.github.viqbgrg.springbootoverseer.shiro;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * shiro 配置文件
 */
@Configuration
public class ShiroConfig {

    @Bean
    public String jwtSalt() {
        return "salt";
    }
}
