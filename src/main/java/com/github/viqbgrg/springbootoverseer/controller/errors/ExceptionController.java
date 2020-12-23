package com.github.viqbgrg.springbootoverseer.controller.errors;

import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.zalando.problem.spring.web.advice.ProblemHandling;

/**
 * Controller advice to translate the server side exceptions to client-friendly json structures.
 * The error response follows RFC7807 - Problem Details for HTTP APIs (https://tools.ietf.org/html/rfc7807).
 *
 * @author bing
 */
@ControllerAdvice
public class ExceptionController implements ProblemHandling {

    private final Environment env;

    public ExceptionController(Environment env) {
        this.env = env;
    }
}