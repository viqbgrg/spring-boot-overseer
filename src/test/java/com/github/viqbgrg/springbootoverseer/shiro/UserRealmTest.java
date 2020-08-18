package com.github.viqbgrg.springbootoverseer.shiro;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

//@SpringBootTest(classes = {ShiroConfig.class})
//@ExtendWith(SpringExtension.class)
//@Import(UserRealm.class)
@SpringBootTest
public class UserRealmTest {

    @Autowired
    private UserRealm userRealm;

    // 密码正确
    @Test
    void login() {
        assertThat(userRealm).isNotNull();
    }

    // todo 用户不存在


    //todo 密码正确

    //todo 密码错误

    //todo username 为admin的账号 拥有 admin customer 的权限

    // todo 其它的用户只有customer的权限
}
