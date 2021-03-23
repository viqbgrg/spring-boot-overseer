package com.github.viqbgrg.springbootoverseer.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.viqbgrg.springbootoverseer.config.TestConfig;
import com.github.viqbgrg.springbootoverseer.domain.dto.XunleiAccountDto;
import com.github.viqbgrg.springbootoverseer.domain.vo.AccountUserInfo;
import com.github.viqbgrg.springbootoverseer.entity.User;
import com.github.viqbgrg.springbootoverseer.service.IAccountService;
import com.github.viqbgrg.springbootoverseer.shiro.AbstractShiroTest;
import org.apache.shiro.subject.Subject;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.zalando.problem.spring.web.autoconfigure.ProblemAutoConfiguration;
import org.zalando.problem.spring.web.autoconfigure.ProblemJacksonAutoConfiguration;
import org.zalando.problem.spring.web.autoconfigure.ProblemJacksonWebMvcAutoConfiguration;

import static org.mockito.Answers.RETURNS_DEEP_STUBS;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
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

    @MockBean
    private IAccountService accountService;

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
        when(accountService.save(any())).thenReturn(true);
        this.mockMvc.perform(post("/account").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsBytes(xunleiAccountDto)))
                .andDo(print()).andExpect(status().isCreated());
    }

    /**
     * 删除迅雷账号
     */
    @Test
    void delAccountTest() throws Exception {
        when(accountService.removeById(any())).thenReturn(true);
        this.mockMvc.perform(delete("/account/1")).andExpect(status().isOk());
    }

    @Test
    void create() {
    }


    @Test
    void collectAll() {
    }

    @Test
    void drawcashAll() {
    }

    @Test
    void getAccountInfo() throws Exception {
        when(accountService.getAccountUserInfo(any())).thenReturn(new AccountUserInfo());
        this.mockMvc.perform(get("/account/accountInfo"))
                .andExpect(status().isOk()).andExpect(jsonPath("$.mpdc", Matchers.notNullValue()));
    }
}