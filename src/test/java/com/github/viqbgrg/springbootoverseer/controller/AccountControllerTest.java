package com.github.viqbgrg.springbootoverseer.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.viqbgrg.springbootoverseer.config.TestConfig;
import com.github.viqbgrg.springbootoverseer.domain.dto.XunleiAccountDto;
import com.github.viqbgrg.springbootoverseer.entity.User;
import com.github.viqbgrg.springbootoverseer.shiro.AbstractShiroTest;
import org.apache.shiro.subject.Subject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.zalando.problem.spring.web.autoconfigure.ProblemAutoConfiguration;
import org.zalando.problem.spring.web.autoconfigure.ProblemJacksonAutoConfiguration;
import org.zalando.problem.spring.web.autoconfigure.ProblemJacksonWebMvcAutoConfiguration;

import static org.mockito.Answers.RETURNS_DEEP_STUBS;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AccountController.class)
@ImportAutoConfiguration(classes = {ProblemAutoConfiguration.class, ProblemJacksonAutoConfiguration.class
        , ProblemJacksonWebMvcAutoConfiguration.class
})
@Import(TestConfig.class)
@ActiveProfiles("test")
class AccountControllerTest extends AbstractShiroTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private XunleiAccountDto xunleiAccountDto;

    @BeforeEach
    void before() {
        Subject subject = mock(Subject.class, RETURNS_DEEP_STUBS);
        User user = new User();
        user.setUsername("username");
        when(subject.getPrincipal()).thenReturn(user);
        setSubject(subject);
    }

    /**
     * 添加迅雷账号
     */
    @Test
    void addAccount() throws Exception {
        System.out.println(xunleiAccountDto);
        this.mockMvc.perform(post("/account").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsBytes(xunleiAccountDto)))
                .andDo(print()).andExpect(status().isOk());
    }

}