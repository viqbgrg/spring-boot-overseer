package com.github.viqbgrg.springbootoverseer.mapper;

import com.baomidou.mybatisplus.autoconfigure.MybatisPlusAutoConfiguration;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.viqbgrg.springbootoverseer.entity.User;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@MybatisTest
@ImportAutoConfiguration(MybatisPlusAutoConfiguration.class)
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserMapperTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    void signIn() {
        User user = new User();
        user.setUsername("username");
        user.setPassword("123456");
        user.setEmail("123456@qq.com");
        user.setCreateAt(LocalDateTime.now());
        user.setLocked(false);
        int insert = userMapper.insert(user);
        assertThat(insert).isGreaterThan(0);
        List<User> users = userMapper.selectList(new LambdaQueryWrapper<User>().eq(User::getUsername, "username"));
        assertThat(users.isEmpty()).isFalse();
    }
}
