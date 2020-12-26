package com.github.viqbgrg.springbootoverseer.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author viqbgrg
 * 用户登陆注册
 */
@Slf4j
@RestController
public class LoginController {

//    private final IUserService userService;
//
//    public LoginController(IUserService userService) {
//        this.userService = userService;
//    }
//
//    @PostMapping("/signIn")
//    public ResponseEntity<Void> signIn(@Validated @RequestBody UserSignInDto user) {
//        User users = new User();
//        BeanUtils.copyProperties(user, users);
//        userService.signIn(users);
//        return ResponseEntity.status(HttpStatus.CREATED).build();
//    }
//
//    @PostMapping("/login")
//    public ResponseEntity<Void> login(@Validated @RequestBody User user) {
//        log.info("进入登陆方法");
//        return ResponseEntity.ok().build();
//    }
}
