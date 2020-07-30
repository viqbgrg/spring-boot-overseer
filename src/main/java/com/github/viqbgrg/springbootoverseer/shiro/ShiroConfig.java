package com.github.viqbgrg.springbootoverseer.shiro;

import org.apache.shiro.realm.Realm;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * shiro 配置文件
 */
@Configuration
public class ShiroConfig {

    @Bean
    protected Realm userRealm() {
        return new UserRealm();
    }
}
