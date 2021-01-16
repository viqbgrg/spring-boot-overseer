package com.github.viqbgrg.springbootoverseer.exception;

import org.zalando.problem.AbstractThrowableProblem;

import static org.zalando.problem.Status.INTERNAL_SERVER_ERROR;

/**
 * @author viqbg
 */
public class NotDelException extends AbstractThrowableProblem {
    public NotDelException() {
        super(null, "删除失败", INTERNAL_SERVER_ERROR);
    }
}
