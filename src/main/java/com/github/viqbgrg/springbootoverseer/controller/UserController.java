package com.github.viqbgrg.springbootoverseer.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author viqbgrg
 * 用户登陆注册
 */
@Slf4j
@RestController("/user")
public class UserController {
    @PostMapping("/login")
    public ResponseEntity<Void> login() {
        log.info("进入登陆方法");
        return ResponseEntity.ok().build();
    }
}
