package com.github.viqbgrg.springbootoverseer.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author viqbgrg
 * 用户登陆注册
 */
@RestController("/user")
public class UserController {
    @PostMapping
    public ResponseEntity<Void> login() {
        return ResponseEntity.ok().build();
    }
}
