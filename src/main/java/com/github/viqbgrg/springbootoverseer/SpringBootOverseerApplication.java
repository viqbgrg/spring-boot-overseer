package com.github.viqbgrg.springbootoverseer;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.github.viqbgrg.springbootoverseer.mapper")
public class SpringBootOverseerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootOverseerApplication.class, args);
    }

}
