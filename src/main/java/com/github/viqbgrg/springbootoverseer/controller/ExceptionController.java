package com.github.viqbgrg.springbootoverseer.controller;

import com.github.viqbgrg.springbootoverseer.exception.MyException;
import org.apache.shiro.ShiroException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
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

    @ResponseBody
    @ExceptionHandler(value = {BindException.class, MethodArgumentNotValidException.class})
    public Object validationExceptionHandler(Exception exception) {

        BindingResult bindResult = null;
        if (exception instanceof BindException) {
            bindResult = ((BindException) exception).getBindingResult();
        } else if (exception instanceof MethodArgumentNotValidException) {
            bindResult = ((MethodArgumentNotValidException) exception).getBindingResult();
        }
        String msg;
        if (bindResult != null && bindResult.hasErrors()) {
            msg = bindResult.getAllErrors().get(0).getDefaultMessage();
            if (msg.contains("NumberFormatException")) {
                msg = "参数类型错误！";
            }
        } else {
            msg = "系统繁忙，请稍后重试...";
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(msg);
    }


    /**
     * 自定义异常
     */
    @ExceptionHandler(MyException.class)
    public ResponseEntity<String> myException(MyException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
}

