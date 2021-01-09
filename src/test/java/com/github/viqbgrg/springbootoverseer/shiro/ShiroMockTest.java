package com.github.viqbgrg.springbootoverseer.shiro;

import com.github.viqbgrg.springbootoverseer.entity.User;
import org.apache.shiro.subject.Subject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.zalando.problem.spring.web.autoconfigure.ProblemAutoConfiguration;
import org.zalando.problem.spring.web.autoconfigure.ProblemJacksonAutoConfiguration;
import org.zalando.problem.spring.web.autoconfigure.ProblemJacksonWebMvcAutoConfiguration;

import static org.mockito.Answers.RETURNS_DEEP_STUBS;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ShiroTestController.class)
@ImportAutoConfiguration(classes = {ProblemAutoConfiguration.class, ProblemJacksonAutoConfiguration.class
        , ProblemJacksonWebMvcAutoConfiguration.class
})
public class ShiroMockTest extends AbstractShiroTest {

    @Autowired
    private MockMvc mvc;


    @Test
    void getShiroSubjectTest() throws Exception {
        Subject subject = mock(Subject.class, RETURNS_DEEP_STUBS);
        User user = new User();
        user.setUsername("username");
        when(subject.getPrincipal()).thenReturn(user);
        setSubject(subject);
        ResultActions resultActions = mvc.perform(get("/shiroSubject").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk()).andExpect(jsonPath("$.username").value("username"));
        resultActions.andReturn().getResponse().setCharacterEncoding("UTF-8");
        //添加断言
        resultActions.andDo(print()).andExpect(status().isOk());
    }

    @Test
    void getTextTest() throws Exception {
        mvc.perform(get("/text").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk()).andExpect(content().string("test"));
    }

    @AfterEach
    public void tearDownSubject() {
        //3. Unbind the subject from the current thread:
        clearSubject();
    }


}
