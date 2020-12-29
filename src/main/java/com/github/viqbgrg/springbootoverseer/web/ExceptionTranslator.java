package com.github.viqbgrg.springbootoverseer.web;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.zalando.problem.Problem;
import org.zalando.problem.spring.web.advice.ProblemHandling;

import javax.annotation.Nullable;

@ControllerAdvice
public class ExceptionTranslator implements ProblemHandling {
    @Override
    public ResponseEntity<Problem> handleNoHandlerFound(NoHandlerFoundException exception, NativeWebRequest request) {
        Problem problem = Problem.builder()
                .withTitle("非法请求")
                .withDetail(String.format("路径: %s 不存在", exception.getRequestURL()))
                .withStatus(defaultConstraintViolationStatus())
                .build();
        return create(exception, problem, request);
    }

    @ExceptionHandler
    public ResponseEntity<Problem> handleAuthenticationException(AuthenticationException ex, NativeWebRequest request) {
        Problem problem = Problem.builder()
                .withTitle("用户名密码错误")
                .withDetail(String.format("用户名密码错误"))
                .withStatus(defaultConstraintViolationStatus())
                .build();
        return create(ex, problem, request);
    }

    @ExceptionHandler
    public ResponseEntity<Problem> handleUnknownAccountException(UnknownAccountException ex, NativeWebRequest request) {
        Problem problem = Problem.builder()
                .withTitle("用户名不存在")
                .withDetail(String.format("用户名不存在"))
                .withStatus(defaultConstraintViolationStatus())
                .build();
        return create(ex, problem, request);
    }
    @Override
    public ResponseEntity<Problem> process(@Nullable ResponseEntity<Problem> entity, NativeWebRequest request) {
        return entity;
    }

}
