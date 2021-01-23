package com.github.viqbgrg.springbootoverseer.mapper;

import com.baomidou.mybatisplus.autoconfigure.MybatisPlusAutoConfiguration;
import com.github.viqbgrg.springbootoverseer.entity.User;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;

import java.time.LocalDateTime;

@MybatisTest
@ImportAutoConfiguration(MybatisPlusAutoConfiguration.class)
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UsersMapperTest {
    @Autowired
    private UserMapper mapper;

    @Test
    void insert() {
        User users = new User();
        users.setUsername("test");
        users.setPassword("123456");
        users.setLocked(false);
        LocalDateTime now = LocalDateTime.now();
        users.setCreateAt(now);
        users.setUpdateAt(now);
        this.mapper.insert(users);
    }
}