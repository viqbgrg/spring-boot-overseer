package com.github.viqbgrg.springbootoverseer.shiro;

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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TestController.class)
@ImportAutoConfiguration(classes = {ProblemAutoConfiguration.class, ProblemJacksonAutoConfiguration.class
        , ProblemJacksonWebMvcAutoConfiguration.class
})
public class ShiroMockTest {

    @Autowired
    private MockMvc mvc;


    @Test
    void getShiroSubjectTest() throws Exception {
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




}
