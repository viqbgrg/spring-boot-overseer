package com.github.viqbgrg.springbootoverseer.exception;

import org.zalando.problem.AbstractThrowableProblem;

import static org.zalando.problem.Status.INTERNAL_SERVER_ERROR;

/**
 * @author viqbg
 */
public class NotSaveException extends AbstractThrowableProblem {
    public NotSaveException() {
        super(null, "保存失败", INTERNAL_SERVER_ERROR);
    }
}
