package com.github.viqbgrg.springbootoverseer.exception;

import org.zalando.problem.AbstractThrowableProblem;

import static java.lang.String.format;
import static org.zalando.problem.Status.BAD_REQUEST;

/**
 * @author viqbg
 */
public class UsernameExistException extends AbstractThrowableProblem {

    public UsernameExistException(String username) {
        super(null, "用户名已注册", BAD_REQUEST, format("用户名%s已注册", username));
    }

}
