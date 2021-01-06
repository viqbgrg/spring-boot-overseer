package com.github.viqbgrg.springbootoverseer.controller;

import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.zalando.problem.spring.web.autoconfigure.ProblemAutoConfiguration;
import org.zalando.problem.spring.web.autoconfigure.ProblemJacksonAutoConfiguration;
import org.zalando.problem.spring.web.autoconfigure.ProblemJacksonWebMvcAutoConfiguration;

@WebMvcTest(UserController.class)
@ImportAutoConfiguration(classes = {ProblemAutoConfiguration.class, ProblemJacksonAutoConfiguration.class
        , ProblemJacksonWebMvcAutoConfiguration.class
})
class AccountControllerTest {


}