package com.github.viqbgrg.springbootoverseer.exception;

import org.zalando.problem.AbstractThrowableProblem;

import static org.zalando.problem.Status.FORBIDDEN;

/**
 * @author viqbg
 */
public class NotFoundException extends AbstractThrowableProblem {
    public NotFoundException(String title) {
        super(null, title, FORBIDDEN);
    }
}
