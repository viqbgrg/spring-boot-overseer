package com.github.viqbgrg.springbootoverseer.controller;

import org.apache.shiro.ShiroException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author viqbgrg
 * 统一异常管理
 */
@RestControllerAdvice
public class ExceptionController {
    /**
     * 捕捉shiro的异常
     */
    @ExceptionHandler(ShiroException.class)
    public ResponseEntity<String> handle401(ShiroException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
    }
}
