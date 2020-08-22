package com.github.viqbgrg.springbootoverseer.shiro;

import org.apache.shiro.realm.Realm;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
class ShiroConfigTest {

    @Autowired
    private List<Realm> realms;

    // todo 测试注入的Realm
    @Test
    void testRealms() {
        assertThat(realms.size()).isEqualTo(2);
    }

    // todo 测试权限

    // todo 测试 url 走的 filter

}