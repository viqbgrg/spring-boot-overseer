package com.github.viqbgrg.springbootoverseer.properties;

import com.github.viqbgrg.springbootoverseer.model.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestProperties {
    @Autowired
    private User customUser;

    @Test
    public void testUser() {
        Assertions.assertThat(customUser.getUsername()).isNotEmpty();
    }
}
