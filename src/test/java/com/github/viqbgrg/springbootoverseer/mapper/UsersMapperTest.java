package com.github.viqbgrg.springbootoverseer.mapper;

import com.github.viqbgrg.springbootoverseer.model.Users;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UsersMapperTest {
    @Autowired
    private UsersMapper mapper;

    @Test
    void insert() {
        Users users = new Users();
        users.setUsername("test");
        users.setPassword("123456");
        this.mapper.insert(users);
    }
}