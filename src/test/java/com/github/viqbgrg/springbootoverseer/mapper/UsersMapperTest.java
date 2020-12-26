package com.github.viqbgrg.springbootoverseer.mapper;

import com.github.viqbgrg.springbootoverseer.user.entity.User;
import com.github.viqbgrg.springbootoverseer.user.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UsersMapperTest {
    @Autowired
    private UserMapper mapper;

    @Test
    void insert() {
        User users = new User();
        users.setUsername("test");
        users.setPassword("123456");
        this.mapper.insert(users);
    }
}