package com.github.viqbgrg.springbootoverseer.shiro;

import com.github.viqbgrg.springbootoverseer.controller.UserController;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zalando.problem.spring.web.autoconfigure.ProblemAutoConfiguration;
import org.zalando.problem.spring.web.autoconfigure.ProblemJacksonAutoConfiguration;
import org.zalando.problem.spring.web.autoconfigure.ProblemJacksonWebMvcAutoConfiguration;

@WebMvcTest(UserController.class)
@ImportAutoConfiguration(classes = {ProblemAutoConfiguration.class, ProblemJacksonAutoConfiguration.class
        , ProblemJacksonWebMvcAutoConfiguration.class
})
public class JWTController {

    @RestController
    public static class TestController {

        @RequestMapping(value = "/jwttest")
        public String test() {
            return "test";
        }
    }
}
